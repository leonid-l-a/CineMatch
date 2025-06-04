package com.example.cinematch.features.auth.data

import retrofit2.http.*

interface CineMatchApi {
    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest,
    ): RegisterResponse

    @FormUrlEncoded
    @POST("auth/jwt/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): LoginResponse

    @GET("auth/me")
    suspend fun getCurrentUser(
        @Header("Authorization") token: String
    ): RegisterResponse
}