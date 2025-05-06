package com.johnnsantana.droidchat.data.repository

import com.johnnsantana.droidchat.model.CreateAccount
import com.johnnsantana.droidchat.data.network.NetworkDataSource
import com.johnnsantana.droidchat.data.network.model.AuthRequest
import com.johnnsantana.droidchat.data.network.model.CreateAccountRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) : AuthRepository {

    override suspend fun signUp(createAccount: CreateAccount) {
        networkDataSource.signUp(
            request = CreateAccountRequest(
                username = createAccount.username,
                password = createAccount.password,
                firstName = createAccount.firstName,
                lastName = createAccount.lastName,
                profilePictureId = createAccount.profilePictureId
            )
        )
    }

    override suspend fun signIn(username: String, password: String) {
        networkDataSource.signIn(
            request = AuthRequest(
                username = username,
                password = password
            )
        )
    }

}