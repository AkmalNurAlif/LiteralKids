package com.example.literalkids.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.literalkids.data.UserProfile

@Entity(tableName = "user_profiles")
data class UserProfileEntity(
    @PrimaryKey
    val id: String,
    val fullName: String,
    val username: String,
    val email: String,
    val phoneNumber: String,
    val userType: String,
    val createdAt: String,
    val updatedAt: String,
    val isActive: Int,
    val lastLogin: String?,
    val lastSyncTime: Long = System.currentTimeMillis()
)

// Extension functions untuk konversi
fun UserProfile.toEntity(): UserProfileEntity {
    return UserProfileEntity(
        id = this.id,
        fullName = this.fullName,
        username = this.username,
        email = this.email,
        phoneNumber = this.phoneNumber,
        userType = this.userType,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        isActive = this.isActive,
        lastLogin = this.lastLogin
    )
}

fun UserProfileEntity.toDomain(): UserProfile {
    return UserProfile(
        id = this.id,
        fullName = this.fullName,
        username = this.username,
        email = this.email,
        phoneNumber = this.phoneNumber,
        userType = this.userType,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        isActive = this.isActive,
        lastLogin = this.lastLogin
    )
} 