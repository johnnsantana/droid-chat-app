package com.johnnsantana.droidchat.ui.feature.signin


data class SigInFormState (
    val email: String = "",
    val emailError: Int? = null,
    val password: String = "",
    val passwordError: Int? = null,
    val isLoading: Boolean = false
)