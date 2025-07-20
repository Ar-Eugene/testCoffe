package com.example.testcoffe.di

import com.example.testcoffe.locations.data.impl.LocationRepositoryImpl
import com.example.testcoffe.locations.data.network.LocationApi
import com.example.testcoffe.locations.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    @Singleton
    fun provideLocationApi(retrofit: Retrofit): LocationApi =
        retrofit.create(LocationApi::class.java)

    @Provides
    @Singleton
    fun provideLocationRepository(api: LocationApi): LocationRepository =
        LocationRepositoryImpl(api)
}