package com.example.cinematch.features.auth.domain

import com.example.cinematch.features.auth.data.NetworkResponse

interface AuthRepository {
    suspend fun signUp(email: String, password: String) : NetworkResponse

    suspend fun login(email: String, password: String): NetworkResponse
}