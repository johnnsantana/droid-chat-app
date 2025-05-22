package com.johnnsantana.droidchat.data.repository

import androidx.paging.PagingData
import com.johnnsantana.droidchat.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(userId: Int): Result<User>

    fun getUsers(limit: Int = 10): Flow<PagingData<User>>
}