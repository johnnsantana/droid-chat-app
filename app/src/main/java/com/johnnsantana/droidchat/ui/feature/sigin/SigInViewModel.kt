package com.johnnsantana.droidchat.ui.feature.sigin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SigInViewModel @Inject constructor(): ViewModel() {

    var formState by mutableStateOf(SigInFormState())
        private set

    fun onEvent(event: SignInFormEvent) {
        when(event) {
            is SignInFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
            }
            is SignInFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
            }
            is SignInFormEvent.Submit -> {
                doSignIn()
            }
        }
    }

    fun doSignIn() {
        formState = formState.copy(isLoading = true)

    }
}