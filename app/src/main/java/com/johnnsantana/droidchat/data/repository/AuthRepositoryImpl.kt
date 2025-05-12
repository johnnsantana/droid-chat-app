package com.johnnsantana.droidchat.data.repository

import com.johnnsantana.droidchat.data.di.IODispatcher
import com.johnnsantana.droidchat.data.manager.TokenManager
import com.johnnsantana.droidchat.model.CreateAccount
import com.johnnsantana.droidchat.data.network.NetworkDataSource
import com.johnnsantana.droidchat.data.network.model.AuthRequest
import com.johnnsantana.droidchat.data.network.model.CreateAccountRequest
import com.johnnsantana.droidchat.model.Image
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val tokenManager: TokenManager,
    @IODispatcher private val IODispatcher: CoroutineDispatcher,
) : AuthRepository {

    override suspend fun getAccessToken(): String? {
        return tokenManager.accessToken.firstOrNull()
    }

    override suspend fun clearAccessToken() {
        withContext(IODispatcher) {
            tokenManager.clearAccessToken()
        }
    }

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

    override suspend fun signIn(username: String, password: String): Result<Unit> {
        return withContext(IODispatcher) {
            runCatching {
                val tokenResponse = networkDataSource.signIn(
                    request = AuthRequest(
                        username = username,
                        password = password
                    )
                )

                tokenManager.saveAccessToken(tokenResponse.token)
            }
        }
    }

    override suspend fun uploadProfilePicture(filePath: String): Result<Image> {
        return withContext(IODispatcher) {
            runCatching {
                val imageResponse = networkDataSource.uploadProfilePicture(filePath)

                Image(
                    id = imageResponse.id,
                    name = imageResponse.name,
                    type = imageResponse.type,
                    url = imageResponse.url
                )

            }
        }
    }

    override suspend fun authenticate(token: String): Result<Unit> {
        return withContext(IODispatcher) {
            runCatching {
                val userResponse = networkDataSource.authenticate(token)
            }
        }
    }

}