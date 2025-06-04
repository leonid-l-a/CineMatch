package com.example.cinematch.features.auth.domain

import com.example.cinematch.features.auth.data.NetworkResponse
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
): LoginUseCase {
    override suspend fun login(
        email: String,
        password: String,
    ): NetworkResponse {
        return repository.login(email, password)
    }

}