package com.productstesttask.data.remote.di

import android.content.Context
import com.productstesttask.data.remote.interceptor.ReadLocalJsonInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideReadLocalJsonInterceptor(
        @ApplicationContext context: Context
    ) = ReadLocalJsonInterceptor(context)

    @Provides
    @Singleton
    fun provideClient(
        readLocalJsonInterceptor: ReadLocalJsonInterceptor
    ) = OkHttpClient.Builder().apply {
        followRedirects(false)
        followSslRedirects(false)
        addInterceptor(readLocalJsonInterceptor)
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("http://api.example.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
