package com.johnnsantana.droidchat.model.fake

import com.johnnsantana.droidchat.model.ChatMessage

val chatMessage1 = ChatMessage(
    autoId = 1,
    id = 1,
    sendId = 1,
    receiver = 1,
    text = "Ola",
    formattedDateTime = "15:00",
    isUnread = true,
    isSelf = true
)
val chatMessage2 = ChatMessage(
    autoId = 2,
    id = 2,
    sendId = 2,
    receiver = 1,
    text = "Olá, tudo bem?",
    formattedDateTime = "15:01",
    isUnread = true,
    isSelf = false
)
val chatMessage3 = ChatMessage(
    autoId = 3,
    id = 3,
    sendId = 1,
    receiver = 2,
    text = "Tudo sim, e com você?",
    formattedDateTime = "15:02",
    isUnread = false,
    isSelf = true
)
val chatMessage4 = ChatMessage(
    autoId = 4,
    id = 4,
    sendId = 2,
    receiver = 1,
    text = "Tudo ótimo também!",
    formattedDateTime = "15:03",
    isUnread = false,
    isSelf = false
)

