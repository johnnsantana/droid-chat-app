package com.johnnsantana.droidchat.data.manager.di

import com.johnnsantana.droidchat.data.manager.SecureTokenManagerImpl
import com.johnnsantana.droidchat.data.manager.TokenManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface TokenManagerModule {

    @Binds
    @Singleton
    fun bindTokenManager(tokenManager: SecureTokenManagerImpl): TokenManager
}
