package com.johnnsantana.droidchat.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val username: String,
    val password: String,
)
