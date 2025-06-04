package com.example.cinematch.features.auth.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class LoginRequest(
    val username: String,
    val password: String,
)

@Serializable
data class LoginResponse(
    @SerialName("access_token")
    val accessToken: String,

    @SerialName("token_type")
    val tokenType: String,
)