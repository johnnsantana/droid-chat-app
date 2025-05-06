package com.johnnsantana.droidchat.data.repository

import com.johnnsantana.droidchat.model.CreateAccount

interface AuthRepository {

    suspend fun signUp(createAccount: CreateAccount): Result<Unit>

    suspend fun signIn(username: String, password: String)
}