package com.example.cinematch.features.auth.domain

import com.example.cinematch.features.auth.data.NetworkResponse

interface LoginUseCase {
    suspend fun login(
        email: String,
        password: String): NetworkResponse
}