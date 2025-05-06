package com.johnnsantana.droidchat.ui.feature.signin


data class SignInFormState (
    val email: String = "",
    val emailError: Int? = null,
    val password: String = "",
    val passwordError: Int? = null,
    val hasError: Boolean = false,
    val isLoading: Boolean = false
)