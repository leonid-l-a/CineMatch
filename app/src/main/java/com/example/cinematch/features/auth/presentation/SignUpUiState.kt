package com.example.cinematch.features.auth.presentation

data class SignUpUiState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val repeatPasswordError: String? = null,
    val isFormValid: Boolean = false,
    val signUpSuccessful: Boolean = false,
    val signUpError: String? = null
)