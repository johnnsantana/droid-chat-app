package com.johnnsantana.droidchat.ui.feature.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johnnsantana.droidchat.data.repository.AuthRepository
import com.johnnsantana.droidchat.model.NetworkException
import com.johnnsantana.droidchat.validator.FormValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.fold

@HiltViewModel
class SigInViewModel @Inject constructor (
    private val formValidator: FormValidator<SignInFormState>,
    private val authRepository: AuthRepository,
): ViewModel() {

    var formState by mutableStateOf(SignInFormState())
        private set

    private val _signInActionFlow = MutableSharedFlow<SignInAction>()
    val signInActionFlow = _signInActionFlow.asSharedFlow()

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

            viewModelScope.launch {
                authRepository.signIn(
                    username = formState.email,
                    password = formState.password
                ).fold(
                    onSuccess = {
                        formState = formState.copy(isLoading = false)

                        _signInActionFlow.emit(SignInAction.Success)
                    },
                    onFailure = {
                        formState = formState.copy(isLoading = false)

                        val error = if (it is NetworkException.ApiException && it.statusCode == 401 ) {
                            SignInAction.Error.UnauthorizedError
                        } else {
                            SignInAction.Error.GenericError
                        }

                        _signInActionFlow.emit(error)
                    }
                )
            }
        }

    }

    private fun isValidForm(): Boolean {
        return !formValidator.validate(formState).also {
            formState = it
        }.hasError
    }

}