package com.johnnsantana.droidchat.data.mapper

import com.johnnsantana.droidchat.data.database.entity.MessageEntity
import com.johnnsantana.droidchat.data.network.model.PaginatedMessageResponse

fun PaginatedMessageResponse.asEntityModel(): List<MessageEntity> = this.messages.map { message ->
    MessageEntity(
        id = message.id,
        isUnread = message.isUnread,
        text = message.text,
        sendId = message.senderId,
        receiveId = message.receiverId,
        timestamp = message.timestamp
    )
}