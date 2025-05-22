package com.johnnsantana.droidchat.model

data class ChatMessage (
    val autoId: Int,
    val id: Int?,
    val sendId: Int,
    val receiver: Int,
    val text: String,
    val formattedDateTime: String,
    val isUnread: Boolean,
    val isSelf: Boolean
)