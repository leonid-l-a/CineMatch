package com.example.cinematch.features.auth.domain

import com.example.cinematch.features.auth.data.NetworkResponse
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
): SignUpUseCase {

    override suspend fun signUp(email: String, password: String): NetworkResponse {
        return repository.signUp(email, password)
    }
}