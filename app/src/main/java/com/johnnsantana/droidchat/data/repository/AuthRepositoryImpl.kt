package com.johnnsantana.droidchat.data.repository

import com.johnnsantana.droidchat.data.di.IODispatcher
import com.johnnsantana.droidchat.model.CreateAccount
import com.johnnsantana.droidchat.data.network.NetworkDataSource
import com.johnnsantana.droidchat.data.network.model.AuthRequest
import com.johnnsantana.droidchat.data.network.model.CreateAccountRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    @IODispatcher private val IODispatcher: CoroutineDispatcher,
) : AuthRepository {

    override suspend fun signUp(createAccount: CreateAccount): Result<Unit> {
        return withContext(IODispatcher) {
            runCatching {
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
        }
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