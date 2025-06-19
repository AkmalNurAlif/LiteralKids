package com.example.literalkids.repository

import android.content.Context
import com.example.literalkids.data.UpdateUserProfileRequest
import com.example.literalkids.data.UserProfile
import com.example.literalkids.database.AppDatabase
import com.example.literalkids.database.UserProfileDao
import com.example.literalkids.database.toEntity
import com.example.literalkids.database.toDomain
import com.example.literalkids.network.NetworkModule
import com.example.literalkids.util.PreferencesManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserProfileRepository(context: Context) {
    
    private val apiService = NetworkModule.apiService
    private val preferencesManager = PreferencesManager(context)
    private val userProfileDao: UserProfileDao = AppDatabase.getDatabase(context).userProfileDao()
    
    private fun getAuthHeader(): String {
        val token = preferencesManager.getAuthToken()
        return "Bearer $token"
    }
    
    suspend fun getUserProfile(userId: String): Result<UserProfile> {
        return try {
            // 1. Coba ambil dari API
            val response = apiService.getUserProfile(userId, getAuthHeader())
            
            if (response.isSuccessful) {
                response.body()?.let { userProfile ->
                    // 2. Cache ke Room database
                    userProfileDao.insertUserProfile(userProfile.toEntity())
                    Result.success(userProfile)
                } ?: Result.failure(Exception("Empty response body"))
            } else {
                // 3. Jika API gagal, coba ambil dari Room database
                val cachedProfile = userProfileDao.getUserProfileById(userId)
                if (cachedProfile != null) {
                    Result.success(cachedProfile.toDomain())
                } else {
                    Result.failure(Exception("Failed to get user profile: ${response.message()}"))
                }
            }
        } catch (e: Exception) {
            // 4. Fallback ke Room database jika network error
            val cachedProfile = userProfileDao.getUserProfileById(userId)
            if (cachedProfile != null) {
                Result.success(cachedProfile.toDomain())
            } else {
                Result.failure(e)
            }
        }
    }
    
    suspend fun updateUserProfile(
        userId: String, 
        request: UpdateUserProfileRequest
    ): Result<String> {
        return try {
            // 1. Update di Room database terlebih dahulu (optimistic update)
            userProfileDao.updateUserProfileFields(
                id = userId,
                fullName = request.fullName,
                email = request.email,
                phoneNumber = request.phoneNumber,
                updatedAt = System.currentTimeMillis().toString()
            )
            
            // 2. Sync ke API
            val response = apiService.updateUserProfile(userId, getAuthHeader(), request)
            
            if (response.isSuccessful) {
                response.body()?.let { responseBody ->
                    Result.success(responseBody["message"] ?: "Update successful")
                } ?: Result.failure(Exception("Empty response body"))
            } else {
                // 3. Jika API gagal, data tetap terupdate di Room (offline-first)
                Result.success("Update saved locally")
            }
        } catch (e: Exception) {
            // 4. Jika ada error, data tetap tersimpan di Room
            Result.success("Update saved locally")
        }
    }
    
    suspend fun getAllUsers(): Result<List<UserProfile>> {
        return try {
            // 1. Coba ambil dari API
            val response = apiService.getAllUsers(getAuthHeader())
            
            if (response.isSuccessful) {
                response.body()?.let { users ->
                    // 2. Cache ke Room database
                    userProfileDao.insertAllUserProfiles(users.map { it.toEntity() })
                    Result.success(users)
                } ?: Result.failure(Exception("Empty response body"))
            } else {
                // 3. Fallback ke Room database
                val cachedUsers = userProfileDao.getAllUserProfiles()
                Result.success(cachedUsers.map { it.toDomain() })
            }
        } catch (e: Exception) {
            // 4. Fallback ke Room database jika network error
            val cachedUsers = userProfileDao.getAllUserProfiles()
            if (cachedUsers.isNotEmpty()) {
                Result.success(cachedUsers.map { it.toDomain() })
            } else {
                Result.failure(e)
            }
        }
    }
    
    // Method baru untuk mendapatkan profile secara real-time dengan Flow
    fun getUserProfileFlow(userId: String): Flow<UserProfile?> {
        return userProfileDao.getUserProfileByIdFlow(userId).map { entity ->
            entity?.toDomain()
        }
    }
    
    // Method untuk mendapatkan semua profiles secara real-time
    fun getAllUserProfilesFlow(): Flow<List<UserProfile>> {
        return userProfileDao.getAllUserProfilesFlow().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    // Method untuk sync data yang belum tersinkronisasi
    suspend fun syncPendingData(): Result<String> {
        return try {
            val oneHourAgo = System.currentTimeMillis() - (60 * 60 * 1000)
            val profilesToSync = userProfileDao.getProfilesNeedingSync(oneHourAgo)
            
            // Sync setiap profile yang belum tersinkronisasi
            profilesToSync.forEach { entity ->
                val request = UpdateUserProfileRequest(
                    fullName = entity.fullName,
                    email = entity.email,
                    phoneNumber = entity.phoneNumber
                )
                // Coba sync ke API tanpa mengubah local data
                try {
                    apiService.updateUserProfile(entity.id, getAuthHeader(), request)
                    // Update lastSyncTime jika berhasil
                    userProfileDao.updateUserProfile(entity.copy(lastSyncTime = System.currentTimeMillis()))
                } catch (e: Exception) {
                    // Skip jika gagal, akan dicoba lagi nanti
                }
            }
            
            Result.success("Sync completed")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    // Helper method untuk mendapatkan current user ID dari token atau preferences
    fun getCurrentUserId(): String? {
        // TODO: Extract user ID dari token atau simpan di preferences saat login
        // Untuk sementara return hardcoded value, nanti bisa diambil dari token payload
        return "1" // Placeholder - seharusnya dari decoded JWT token
    }
} 