package com.johnnsantana.droidchat.data.mapper

import com.johnnsantana.droidchat.data.network.model.PaginatedUserResponse
import com.johnnsantana.droidchat.model.User

fun PaginatedUserResponse.asDomainModel(): List<User> = this.users.map { userResponse ->
    User(
        id = userResponse.id,
        self = false,
        firstName = userResponse.firstName,
        lastName = userResponse.lastName,
        profilePictureUrl = userResponse.profilePictureUrl,
        username = userResponse.username
    )
}