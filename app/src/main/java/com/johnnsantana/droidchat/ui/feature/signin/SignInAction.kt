package com.johnnsantana.droidchat.ui.feature.signin

interface SignInAction {
    data object Success: SignInAction

    sealed interface Error: SignInAction {
        data object GenericError : Error
        data object UnauthorizedError: Error
    }
}