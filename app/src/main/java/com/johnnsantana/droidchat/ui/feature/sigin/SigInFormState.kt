package com.johnnsantana.droidchat.ui.feature.sigin

data class SigInFormState (
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false
)