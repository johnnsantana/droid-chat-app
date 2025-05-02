package com.johnnsantana.droidchat.ui.feature.sigin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.johnnsantana.droidchat.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SigInViewModel @Inject constructor(): ViewModel() {

    var formState by mutableStateOf(SigInFormState())
        private set

    fun onEvent(event: SignInFormEvent) {
        when(event) {
            is SignInFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email, emailError = null)
            }
            is SignInFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password, passwordError = null)
            }
            is SignInFormEvent.Submit -> {
                doSignIn()
            }
        }
    }

    fun doSignIn() {
        var isFormValid = true
        if (formState.email.isBlank()) {
            formState = formState.copy(emailError = R.string.error_message_email_invalid)
            isFormValid = false
        }

      if (isFormValid) {
          formState = formState.copy(isLoading = true)
      }

    }

}