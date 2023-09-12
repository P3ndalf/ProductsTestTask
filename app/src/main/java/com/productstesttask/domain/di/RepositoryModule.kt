package com.productstesttask.domain.di

import com.productstesttask.domain.repository.CardRepository
import com.productstesttask.domain.repository.ICardRepository
import com.productstesttask.domain.repository.IProductsRepository
import com.productstesttask.domain.repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductsRepository(repository: ProductsRepository): IProductsRepository

    @Binds
    abstract fun bindCardRepository(repository: CardRepository): ICardRepository
}
