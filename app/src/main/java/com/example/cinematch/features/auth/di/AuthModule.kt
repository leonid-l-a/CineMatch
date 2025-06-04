package com.example.cinematch.features.auth.di

import com.example.cinematch.features.auth.data.ApiClient
import com.example.cinematch.features.auth.data.AuthRepositoryImpl
import com.example.cinematch.features.auth.domain.AuthRepository
import com.example.cinematch.features.auth.domain.SignUpUseCase
import com.example.cinematch.features.auth.domain.SignUpUseCaseImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json { ignoreUnknownKeys = true }
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(json: Json): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://cinematch.ddns.net/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiClient(retrofit: Retrofit): ApiClient {
        return ApiClient(retrofit)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(apiClient: ApiClient): AuthRepository {
        return AuthRepositoryImpl(apiClient)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(repository: AuthRepository): SignUpUseCase {
        return SignUpUseCaseImpl(repository)
    }
}
