package com.example.literalkids.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {
    
    @Query("SELECT * FROM user_profiles WHERE id = :id")
    suspend fun getUserProfileById(id: String): UserProfileEntity?
    
    @Query("SELECT * FROM user_profiles WHERE id = :id")
    fun getUserProfileByIdFlow(id: String): Flow<UserProfileEntity?>
    
    @Query("SELECT * FROM user_profiles WHERE userType = :userType")
    suspend fun getUserProfilesByType(userType: String): List<UserProfileEntity>
    
    @Query("SELECT * FROM user_profiles")
    suspend fun getAllUserProfiles(): List<UserProfileEntity>
    
    @Query("SELECT * FROM user_profiles")
    fun getAllUserProfilesFlow(): Flow<List<UserProfileEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(userProfile: UserProfileEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUserProfiles(userProfiles: List<UserProfileEntity>)
    
    @Update
    suspend fun updateUserProfile(userProfile: UserProfileEntity)
    
    @Query("UPDATE user_profiles SET fullName = :fullName, email = :email, phoneNumber = :phoneNumber, updatedAt = :updatedAt, lastSyncTime = :lastSyncTime WHERE id = :id")
    suspend fun updateUserProfileFields(
        id: String,
        fullName: String,
        email: String,
        phoneNumber: String,
        updatedAt: String,
        lastSyncTime: Long = System.currentTimeMillis()
    )
    
    @Delete
    suspend fun deleteUserProfile(userProfile: UserProfileEntity)
    
    @Query("DELETE FROM user_profiles WHERE id = :id")
    suspend fun deleteUserProfileById(id: String)
    
    @Query("DELETE FROM user_profiles")
    suspend fun deleteAllUserProfiles()
    
    @Query("SELECT COUNT(*) FROM user_profiles")
    suspend fun getUserProfileCount(): Int
    
    @Query("SELECT * FROM user_profiles WHERE lastSyncTime < :timestamp")
    suspend fun getProfilesNeedingSync(timestamp: Long): List<UserProfileEntity>
} 