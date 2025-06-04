package com.example.cinematch.features.auth.data

sealed class NetworkResponse {
    data class Success(val data: Any) : NetworkResponse()
    data class Error(val message: String) : NetworkResponse()
}
