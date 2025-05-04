package com.johnnsantana.droidchat.ui.feature.sigin

sealed interface SignInFormEvent {
    data class EmailChanged(val email: String) : SignInFormEvent
    data class PasswordChanged(val password: String) : SignInFormEvent
    data object Submit : SignInFormEvent
}