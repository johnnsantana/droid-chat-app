package com.johnnsantana.droidchat.data.manager

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.johnnsantana.droidchat.data.datasource.TokensKeys
import com.johnnsantana.droidchat.data.datasource.tokenDataStore
import com.johnnsantana.droidchat.data.di.IODispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TokenManagerImpl @Inject constructor(
   @ApplicationContext private val context: Context,
   @IODispatcher private val IODispatcher: CoroutineDispatcher
) : TokenManager {

    private val tokenDataStore = context.tokenDataStore

    override val accessToken: Flow<String>
        get() = tokenDataStore.data.map { preferences ->
            preferences[TokensKeys.ACCESS_TOKEN] ?: ""
        }

    override suspend fun saveAccessToken(token: String) {
        withContext(IODispatcher) {
            tokenDataStore.edit { preferences ->
                preferences[TokensKeys.ACCESS_TOKEN] = token
            }
        }
    }

    override suspend fun clearAccessToken() {
        withContext(IODispatcher) {
            tokenDataStore.edit { preferences ->
                preferences.remove(TokensKeys.ACCESS_TOKEN)
            }
        }
    }
}