package com.johnnsantana.droidchat.data.database.di

import com.johnnsantana.droidchat.data.database.DatabaseDataSource
import com.johnnsantana.droidchat.data.database.DatabaseDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseDataSourceModule {
    @Binds
    @Singleton
    fun bindDatabaseDataSource(
        databaseDataSource: DatabaseDataSourceImpl
    ): DatabaseDataSource
}