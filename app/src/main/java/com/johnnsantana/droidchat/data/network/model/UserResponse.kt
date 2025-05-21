package com.johnnsantana.droidchat.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginatedUserResponse(
    @SerialName("conversations")
    val users: List<UserResponse>,
    val hasMore: Boolean,
    val total: Int
)



@Serializable
data class UserResponse (
    val id: Int,
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val profilePictureUrl: String?
)