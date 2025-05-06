package com.johnnsantana.droidchat.data.repository

import com.johnnsantana.droidchat.data.model.CreateAccount

interface AuthRepository {

    suspend fun signUp(createAccount: CreateAccount)

    suspend fun signIn(username: String, password: String)
}