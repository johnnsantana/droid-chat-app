package com.johnnsantana.droidchat.data.manager.token

import android.content.Context
import com.johnnsantana.droidchat.data.datasource.TokensKeys
import com.johnnsantana.droidchat.data.di.IODispatcher
import com.johnnsantana.droidchat.data.manager.CryptoManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SecureTokenManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @IODispatcher private val IODispatcher: CoroutineDispatcher,
) : TokenManager {
    override val accessToken: Flow<String>
        get() = flowOf(CryptoManager.decryptData(context, TokensKeys.ACCESS_TOKEN.name))

    override suspend fun saveAccessToken(token: String) {
        withContext(IODispatcher) {
            CryptoManager.encryptData(context, TokensKeys.ACCESS_TOKEN.name, "")
        }
    }

    override suspend fun clearAccessToken() {
        withContext(IODispatcher) {
            CryptoManager.encryptData(context, TokensKeys.ACCESS_TOKEN.name, "")
        }
    }

}