package com.johnnsantana.droidchat.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val autoId: Int = 0,
    val id: Int?,
    @ColumnInfo(name = "is_unread")
    val isUnread: Boolean,
    val text: String,
    @ColumnInfo(name = "sender_id")
    val sendId: Int,
    @ColumnInfo(name = "receiver_id")
    val receiveId: Int,
    val timestamp: Long
)
