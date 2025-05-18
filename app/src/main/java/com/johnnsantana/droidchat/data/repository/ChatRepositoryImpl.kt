package com.johnnsantana.droidchat.data.repository

import com.johnnsantana.droidchat.data.di.IODispatcher
import com.johnnsantana.droidchat.data.manager.selfuser.SelfUserManager
import com.johnnsantana.droidchat.data.manager.token.TokenManager
import com.johnnsantana.droidchat.data.mapper.asDomainModel
import com.johnnsantana.droidchat.data.network.NetworkDataSource
import com.johnnsantana.droidchat.data.network.model.PaginationParams
import com.johnnsantana.droidchat.model.Chat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor (
    private val networkDataSource: NetworkDataSource,
    private val tokenManager: TokenManager,
    @IODispatcher private val IODispatcher: CoroutineDispatcher,
    private val selfUserManager: SelfUserManager
) : ChatRepository {

    override suspend fun getChats(
        offset: Int,
        limit: Int
    ): Result<List<Chat>> {
       return withContext(IODispatcher) {
           runCatching {
               val token = tokenManager.accessToken.firstOrNull() ?: ""

               val paginatedChatResponse = networkDataSource.getChats(
                   token = token,
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
}
