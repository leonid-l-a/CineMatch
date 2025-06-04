package com.example.cinematch.features.auth.data

import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException

class ApiClient(retrofit: Retrofit) : NetworkClient {

    private val apiService = retrofit.create(CineMatchApi::class.java)

    override suspend fun doRequest(dto: Any): NetworkResponse {
        return try {
            when (dto) {
                is RegisterRequest -> {
                    val response = apiService.register(dto)
                    NetworkResponse.Success(response)
                }

                is LoginRequest -> {
                    val response = apiService.login(dto.username, dto.password)
                    NetworkResponse.Success(response)
                }

                is TokenRequest -> {
                    val response = apiService.getCurrentUser("Bearer ${dto.token}")
                    NetworkResponse.Success(response)
                }

                else -> {
                    NetworkResponse.Error("Unknown request type: ${dto::class.simpleName}")
                }
            }
        } catch (e: HttpException) {
            NetworkResponse.Error("HTTP ${e.code()}: ${e.message()}")
        } catch (e: IOException) {
            NetworkResponse.Error("Network error: ${e.message}")
        } catch (e: Exception) {
            NetworkResponse.Error("Unexpected error: ${e.message}")
        }
    }
}
