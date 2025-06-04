package com.example.cinematch.features.auth.data

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val id: String,
    val email: String,
    val isActive: Boolean = false,
    val isSuperuser: Boolean = false,
    val isVerified: Boolean = false,
)

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String,
    val isActive: Boolean = true,
    val isSuperuser: Boolean = false,
    val isVerified: Boolean = false,
)
