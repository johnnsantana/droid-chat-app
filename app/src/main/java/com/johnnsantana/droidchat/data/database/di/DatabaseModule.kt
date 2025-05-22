package com.johnnsantana.droidchat.data.database.di

import android.content.Context
import androidx.room.Room
import com.johnnsantana.droidchat.data.database.DroidChatDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DroidChatDataBase =
        Room.databaseBuilder(
            context,
            DroidChatDataBase::class.java,
            "droidchat_database"
        ).build()

}