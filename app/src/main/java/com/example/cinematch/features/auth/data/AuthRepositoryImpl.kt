package com.example.cinematch.features.auth.data

import com.example.cinematch.features.auth.domain.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiClient: ApiClient,
) : AuthRepository {
    override suspend fun signUp(email: String, password: String): NetworkResponse {
        return apiClient.doRequest(
            RegisterRequest(
                email,
                password
            )
        )
    }

    override suspend fun login(email: String, password: String): NetworkResponse {
        return apiClient.doRequest(
            LoginRequest(
                email,
                password
            )
        )
    }
}
