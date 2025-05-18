package com.johnnsantana.droidchat.data.manager.selfuser

import android.content.Context
import com.johnnsantana.droidchat.SelfUser
import com.johnnsantana.droidchat.data.datasource.selfUserStore
import com.johnnsantana.droidchat.data.di.IODispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SelfUserManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @IODispatcher private val IODispatcher: CoroutineDispatcher
) :SelfUserManager {

    private val selfUserStore = context.selfUserStore

    override val selfUserFlow: Flow<SelfUser>
        get() = selfUserStore.data

    override suspend fun saveSelfUserData(
        id: Int,
        firstName: String,
        lastName: String,
        profilePictureUrl: String,
        username: String
    ) {
        withContext(IODispatcher) {
            selfUserStore.updateData {
                it.toBuilder()
                    .setId(id)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setProfilePictureUrl(profilePictureUrl)
                    .setUsername(username)
                    .build()
            }
        }
    }

    override suspend fun clearSelfUser() {
        withContext(IODispatcher) {
            selfUserStore.updateData {
                it.toBuilder()
                    .clearFirstName()
                    .clearLastName()
                    .clearProfilePictureUrl()
                    .clearUsername()
                    .build()
            }
        }
    }
}