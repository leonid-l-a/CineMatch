package com.example.cinematch.features.auth.presentation

sealed class SignUpIntent {
    data class EmailChanged(val email: String) : SignUpIntent()
    data class PasswordChanged(val password: String) : SignUpIntent()
    data class RepeatPasswordChanged(val password: String) : SignUpIntent()
    object SignUpClicked : SignUpIntent()
}