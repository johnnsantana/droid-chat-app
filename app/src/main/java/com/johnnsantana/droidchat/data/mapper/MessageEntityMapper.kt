package com.johnnsantana.droidchat.data.mapper

import com.johnnsantana.droidchat.data.database.entity.MessageEntity
import com.johnnsantana.droidchat.model.ChatMessage


fun MessageEntity.asDomainModel(selfUserId: Int?): ChatMessage {
    return ChatMessage(
        autoId = this.autoId,
        id = this.id,
        sendId = this.sendId,
        receiver = this.receiveId,
        text = this.text,
        formattedDateTime = this.timestamp.toTimestamp(),
        isUnread = this.isUnread,
        isSelf = this.sendId == selfUserId,
    )
}