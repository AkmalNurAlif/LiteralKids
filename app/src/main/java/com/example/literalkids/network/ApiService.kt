package com.example.literalkids.network

import com.example.literalkids.data.LoginRequest
import com.example.literalkids.data.LoginResponse
import com.example.literalkids.data.UpdateUserProfileRequest
import com.example.literalkids.data.UserProfile
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
    
    // User Profile endpoints
    @GET("api/userprofile/{id}")
    suspend fun getUserProfile(
        @Path("id") userId: String,
        @Header("Authorization") authorization: String
    ): Response<UserProfile>
    
    @PUT("api/userprofile/{id}")
    suspend fun updateUserProfile(
        @Path("id") userId: String,
        @Header("Authorization") authorization: String,
        @Body request: UpdateUserProfileRequest
    ): Response<Map<String, String>>
    
    @GET("api/userprofile")
    suspend fun getAllUsers(
        @Header("Authorization") authorization: String
    ): Response<List<UserProfile>>
} 