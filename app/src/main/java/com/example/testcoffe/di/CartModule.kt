package com.example.testcoffe.di

import com.example.testcoffe.order.data.repositoryimpl.CartRepositoryImpl
import com.example.testcoffe.order.domain.repository.CartRepository
import com.example.testcoffe.order.domain.usecase.AddItemToCartUseCase
import com.example.testcoffe.order.domain.usecase.ClearCartUseCase
import com.example.testcoffe.order.domain.usecase.GetCartUseCase
import com.example.testcoffe.order.domain.usecase.UpdateItemCountUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartModule {

    @Singleton
    @Provides
    fun provideCartRepository(): CartRepository = CartRepositoryImpl()

    @Provides
    fun provideAddItemToCartUseCase(repo: CartRepository) = AddItemToCartUseCase(repo)
    @Provides
    fun provideUpdateItemCountUseCase(repo: CartRepository) = UpdateItemCountUseCase(repo)
    @Provides
    fun provideClearCartUseCase(repo: CartRepository) = ClearCartUseCase(repo)
    @Provides
    fun provideGetCartUseCase(repo: CartRepository) = GetCartUseCase(repo)
}