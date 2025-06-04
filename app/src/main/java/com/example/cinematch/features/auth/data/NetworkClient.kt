package com.example.cinematch.features.auth.data

interface NetworkClient {
    suspend fun doRequest(dto: Any): NetworkResponse
}