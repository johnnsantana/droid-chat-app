package com.johnnsantana.droidchat.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.johnnsantana.droidchat.data.database.dao.MessageDao
import com.johnnsantana.droidchat.data.database.dao.MessageRemoteKeyDao
import com.johnnsantana.droidchat.data.database.entity.MessageEntity
import com.johnnsantana.droidchat.data.database.entity.MessageRemoteKeyEntity

@Database(
    entities = [
        MessageEntity::class,
        MessageRemoteKeyEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class DroidChatDataBase : RoomDatabase() {
    abstract fun messageDao(): MessageDao

    abstract fun messageRemoteKeyDao(): MessageRemoteKeyDao
}