package com.johnnsantana.droidchat.data.repository

import com.johnnsantana.droidchat.model.Chat

interface ChatRepository {

    suspend fun getChats(offset: Int, limit: Int): Result<List<Chat>>
}