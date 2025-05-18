package com.johnnsantana.droidchat.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse (
    val id: Int,
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val profilePictureUrl: String?
)