package com.johnnsantana.droidchat.ui.feature.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.johnnsantana.droidchat.R
import com.johnnsantana.droidchat.validator.FormValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class SigInViewModel (
    private val formValidator: FormValidator<SignInFormState>
): ViewModel() {

    var formState by mutableStateOf(SignInFormState())
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
        if (isValidForm()) {
            formState = formState.copy(isLoading =  true)
        }
    }

    private fun isValidForm(): Boolean {
        return !formValidator.validate(formState).also {
            formState = it
        }.hasError
    }

}