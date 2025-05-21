package com.johnnsantana.droidchat.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.johnnsantana.droidchat.data.mapper.asDomainModel
import com.johnnsantana.droidchat.data.network.NetworkDataSource
import com.johnnsantana.droidchat.data.network.model.PaginationParams
import com.johnnsantana.droidchat.model.User
import javax.inject.Inject

class UserPagingSource @Inject constructor(
    private val networkDataSource: NetworkDataSource
): PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
       return try {
            val offset = params.key ?: 0
            val response = networkDataSource.getUsers(
                paginationParams = PaginationParams(
                    offset = offset.toString(),
                    limit = params.loadSize.toString()
                )
            )
            return LoadResult.Page(
                data = response.asDomainModel(),
                prevKey = null,
                nextKey = if (response.hasMore) offset + params.loadSize else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(state.config.pageSize) ?: anchorPage?.nextKey?.minus(state.config.pageSize)
        }
    }

}