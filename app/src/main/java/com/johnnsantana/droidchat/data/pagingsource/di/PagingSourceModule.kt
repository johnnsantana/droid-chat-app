package com.johnnsantana.droidchat.data.pagingsource.di

import androidx.paging.PagingSource
import com.johnnsantana.droidchat.data.network.NetworkDataSource
import com.johnnsantana.droidchat.data.pagingsource.UserPagingSource
import com.johnnsantana.droidchat.model.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PagingSourceModule {

    @Provides
    fun provideUserPagingSource(
        networkDataSource: NetworkDataSource
    ): PagingSource<Int, User> = UserPagingSource(
        networkDataSource = networkDataSource
    )
}