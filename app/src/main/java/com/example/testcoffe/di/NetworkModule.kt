package com.example.testcoffe.di

import com.example.testcoffe.register.data.network.AuthApi
import com.example.testcoffe.register.data.repositoryImpl.AuthRepositoryImpl
import com.example.testcoffe.register.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl() = "http://212.41.30.90:35005/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi): AuthRepository =
        AuthRepositoryImpl(api)
}