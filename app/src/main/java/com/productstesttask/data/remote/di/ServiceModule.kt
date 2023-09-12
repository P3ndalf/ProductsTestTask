package com.productstesttask.data.remote.di

import com.productstesttask.data.remote.api.IProductsApi
import com.productstesttask.data.remote.service.IProductService
import com.productstesttask.data.remote.service.ProductsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideProductsService(api: IProductsApi): IProductService = ProductsService(api)
}
