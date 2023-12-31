package com.productstesttask.data.remote.di

import com.productstesttask.data.remote.api.IProductsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideProductsApi(retrofit: Retrofit): IProductsApi = retrofit.create(IProductsApi::class.java)
}
