package com.example.cinematch.features.auth.domain

import com.example.cinematch.features.auth.data.NetworkResponse

interface SignUpUseCase {
    suspend fun signUp(email: String, password: String): NetworkResponse
}