package com.johnnsantana.droidchat.data.mapper

import com.johnnsantana.droidchat.data.network.model.PaginatedChatResponse
import com.johnnsantana.droidchat.model.Chat
import com.johnnsantana.droidchat.model.User
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun PaginatedChatResponse.asDomainModel(selfUserId: Int?): List<Chat> = this.chats.map { chatResponse ->
    Chat(
        id = chatResponse.id,
        lastMessage = chatResponse.lastMessages,
        members = chatResponse.members.map { userResponse ->
            User(
                id = userResponse.id,
                self = userResponse.id == selfUserId,
                firstName = userResponse.firstName,
                lastName = userResponse.lastName,
                profilePictureUrl = userResponse.profilePictureUrl,
                username = userResponse.username
            )
        },
        unreadCount = chatResponse.unreadCount,
        timestamp = chatResponse.updatedAt.toTimestamp()
    )
}


