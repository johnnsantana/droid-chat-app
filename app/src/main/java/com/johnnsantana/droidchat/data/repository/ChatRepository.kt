package com.johnnsantana.droidchat.data.repository

import androidx.paging.PagingData
import com.johnnsantana.droidchat.model.Chat
import com.johnnsantana.droidchat.model.ChatMessage
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun getChats(offset: Int, limit: Int): Result<List<Chat>>

    fun getPagedMessages(receiverId: Int): Flow<PagingData<ChatMessage>>

    suspend fun sendMessage(receiverId: Int, message: String): Result<Unit>

}