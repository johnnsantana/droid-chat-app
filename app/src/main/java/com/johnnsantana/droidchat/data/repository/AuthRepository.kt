package com.johnnsantana.droidchat.data.repository

import com.johnnsantana.droidchat.model.CreateAccount
import com.johnnsantana.droidchat.model.Image

interface AuthRepository {

    suspend fun signUp(createAccount: CreateAccount): Result<Unit>

    suspend fun signIn(username: String, password: String): Result<Unit>

    suspend fun uploadProfilePicture(filePath: String): Result<Image>
}