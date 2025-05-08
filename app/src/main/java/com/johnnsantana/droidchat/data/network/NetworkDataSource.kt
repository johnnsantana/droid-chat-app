package com.johnnsantana.droidchat.data.network

import com.johnnsantana.droidchat.data.network.model.AuthRequest
import com.johnnsantana.droidchat.data.network.model.CreateAccountRequest
import com.johnnsantana.droidchat.data.network.model.ImageResponse
import com.johnnsantana.droidchat.data.network.model.TokenResponse

interface NetworkDataSource {
    suspend fun signUp(request: CreateAccountRequest)

    suspend fun signIn(request: AuthRequest) : TokenResponse

    suspend fun uploadProfilePicture(filePath: String): ImageResponse
}