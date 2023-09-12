package com.productstesttask.data.local.di

import android.content.Context
import com.productstesttask.data.local.dao.CardDao
import com.productstesttask.data.local.db.CardDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun provideCardDao(@ApplicationContext context: Context): CardDao =
        CardDb.getInstance(context).cardDao()
}
