package com.kb.cleanarchitecturedemo.di

import com.kb.cleanarchitecturedemo.manageProduct.data.repository.ProductRepositoryImpl
import com.kb.cleanarchitecturedemo.manageProduct.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideProductsRepository(impl: ProductRepositoryImpl): ProductRepository
}