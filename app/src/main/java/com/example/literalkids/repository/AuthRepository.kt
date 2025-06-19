package com.example.literalkids.repository

import android.content.Context
import com.example.literalkids.data.LoginRequest
import com.example.literalkids.data.LoginResponse
import com.example.literalkids.network.NetworkModule
import com.example.literalkids.util.PreferencesManager
import retrofit2.Response

class AuthRepository(context: Context) {
    
    private val apiService = NetworkModule.apiService
    private val preferencesManager = PreferencesManager(context)
    
    suspend fun login(email: String, password: String): Result<LoginResponse> {
        return try {
            val request = LoginRequest(email, password)
            val response: Response<LoginResponse> = apiService.login(request)
            
            if (response.isSuccessful) {
                response.body()?.let { loginResponse ->
                    // Simpan token ke SharedPreferences
                    preferencesManager.saveAuthToken(loginResponse.token)
                    Result.success(loginResponse)
                } ?: Result.failure(Exception("Empty response body"))
            } else {
                Result.failure(Exception("Login failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 