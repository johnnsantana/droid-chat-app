package com.johnnsantana.droidchat.data.network

import com.johnnsantana.droidchat.data.network.model.AuthRequest
import com.johnnsantana.droidchat.data.network.model.CreateAccountRequest
import com.johnnsantana.droidchat.data.network.model.ImageResponse
import com.johnnsantana.droidchat.data.network.model.PaginatedChatResponse
import com.johnnsantana.droidchat.data.network.model.PaginationParams
import com.johnnsantana.droidchat.data.network.model.TokenResponse
import com.johnnsantana.droidchat.data.network.model.UserResponse

interface NetworkDataSource {
    suspend fun signUp(request: CreateAccountRequest)

    suspend fun signIn(request: AuthRequest) : TokenResponse

    suspend fun uploadProfilePicture(filePath: String): ImageResponse

    suspend fun authenticate(token: String): UserResponse

    suspend fun getChats(token: String, paginationParams: PaginationParams): PaginatedChatResponse
}