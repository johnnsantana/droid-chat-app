package com.johnnsantana.droidchat.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.johnnsantana.droidchat.data.database.DatabaseDataSource
import com.johnnsantana.droidchat.data.database.DroidChatDataBase
import com.johnnsantana.droidchat.data.database.entity.MessageEntity
import com.johnnsantana.droidchat.data.di.IODispatcher
import com.johnnsantana.droidchat.data.manager.selfuser.SelfUserManager
import com.johnnsantana.droidchat.data.manager.token.TokenManager
import com.johnnsantana.droidchat.data.mapper.asDomainModel
import com.johnnsantana.droidchat.data.network.NetworkDataSource
import com.johnnsantana.droidchat.data.network.model.PaginationParams
import com.johnnsantana.droidchat.data.pagingsource.MessageRemoteMediator
import com.johnnsantana.droidchat.model.Chat
import com.johnnsantana.droidchat.model.ChatMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

class ChatRepositoryImpl @Inject constructor (
    private val networkDataSource: NetworkDataSource,
    @IODispatcher private val IODispatcher: CoroutineDispatcher,
    private val databaseDataSource: DatabaseDataSource,
    private val database: DroidChatDataBase,
    private val selfUserManager: SelfUserManager
) : ChatRepository {

    override suspend fun getChats(
        offset: Int,
        limit: Int
    ): Result<List<Chat>> {
       return withContext(IODispatcher) {
           runCatching {

               val paginatedChatResponse = networkDataSource.getChats(
                   paginationParams = PaginationParams(
                       offset = offset.toString(),
                       limit = offset.toString(),
                   )
               )
               val selfUser = selfUserManager.selfUserFlow.firstOrNull()

               paginatedChatResponse.asDomainModel(selfUser?.id)
           }
       }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getPagedMessages(receiverId: Int): Flow<PagingData<ChatMessage>> {
        val selfUserId = runBlocking { selfUserManager.selfUserFlow.firstOrNull() }
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            remoteMediator = MessageRemoteMediator(
                networkDataSource = networkDataSource,
                databaseDataSource = databaseDataSource,
                database = database,
                receiverId = receiverId
            ),
            pagingSourceFactory = {
                databaseDataSource.getPagedMessages(receiverId)
            }
        ).flow.map {
            it.map { messageEntity ->
                messageEntity.asDomainModel(selfUserId = selfUserId?.id)
            }
        }
    }

    @OptIn(ExperimentalTime::class)
    override suspend fun sendMessage(
        receiverId: Int,
        message: String
    ): Result<Unit> {
        val selfUserId = selfUserManager.selfUserFlow.firstOrNull()

        val messageEntity = MessageEntity(
            id = null,
            isUnread = false,
            sendId = selfUserId?.id ?: 0,
            text = message,
            receiveId = receiverId,
            timestamp = Clock.System.now().toEpochMilliseconds()
        )
        databaseDataSource.insertMessages(listOf(messageEntity))
    }
}
