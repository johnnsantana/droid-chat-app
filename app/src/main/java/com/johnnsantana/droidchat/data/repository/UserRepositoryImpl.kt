package com.johnnsantana.droidchat.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.johnnsantana.droidchat.data.network.NetworkDataSource
import com.johnnsantana.droidchat.data.pagingsource.UserPagingSource
import com.johnnsantana.droidchat.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : UserRepository {
    override suspend fun getUser(userId: Int): Result<User> {
        return TODO()
    }

    override fun getUsers(limit: Int): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                prefetchDistance = 1,
                pageSize = limit,
                initialLoadSize = limit,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                UserPagingSource(
                    networkDataSource = networkDataSource
                )
            }
        ).flow
    }
}