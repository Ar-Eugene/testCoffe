package com.example.testcoffe.di

import com.example.testcoffe.locations.data.impl.MenuRepositoryImpl
import com.example.testcoffe.menu.data.network.MenuApi
import com.example.testcoffe.menu.domain.repository.MenuRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MenuModule {

    @Provides
    @Singleton
    fun provideMenuApi(retrofit: Retrofit): MenuApi =
        retrofit.create(MenuApi::class.java)

    @Provides
    @Singleton
    fun provideMenuRepository(api: MenuApi): MenuRepository =
        MenuRepositoryImpl(api)
}