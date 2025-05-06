package com.johnnsantana.droidchat.ui.feature.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johnnsantana.droidchat.R
import com.johnnsantana.droidchat.model.CreateAccount
import com.johnnsantana.droidchat.data.repository.AuthRepository
import com.johnnsantana.droidchat.model.NetworkException
import com.johnnsantana.droidchat.validator.FormValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val formValidator: FormValidator<SignUpFormState>,
    private val authRepository: AuthRepository,
): ViewModel() {

    var formState by mutableStateOf(SignUpFormState())
        private set

    fun onFormEvent(event: SignUpFormEvent) {
        when (event) {
            is SignUpFormEvent.ProfilePhotoUriChanged -> {
                formState = formState.copy(profilePictureUri = event.uri)
            }
            is SignUpFormEvent.FirstNameChanged -> {
                formState = formState.copy(firstName = event.firstName)
            }
            is SignUpFormEvent.LastNameChanged -> {
                formState = formState.copy(lastName = event.lastName)
            }
            is SignUpFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
            }
            is SignUpFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
                updatePasswordExtraText()
            }
            is SignUpFormEvent.PasswordConfirmationChanged -> {
                formState = formState.copy(passwordConfirmation = event.passwordConfirmation)
                updatePasswordExtraText()
            }
            SignUpFormEvent.OpenProfilePictureOptionsModalBottomSheet -> {
                formState = formState.copy(isProfilePictureModalBottomSheetOpen = true)
            }
            SignUpFormEvent.CloseProfilePictureOptionsModalBottomSheet -> {
                formState = formState.copy(isProfilePictureModalBottomSheetOpen = false)
            }
            is SignUpFormEvent.Submit -> {
                doSignUp()
            }
        }
    }

    private fun updatePasswordExtraText() {
        formState = formState.copy(
            passwordExtraText = if (formState.password.isNotEmpty()
                && formState.password == formState.passwordConfirmation ) {
                R.string.feature_sign_up_passwords_match
            } else null
        )
    }

    private fun doSignUp() {
        if (isValidForm()) {
            formState = formState.copy(isLoading =  true)
            viewModelScope.launch {
                authRepository.signUp(
                    createAccount = CreateAccount(
                        username = formState.email,
                        password = formState.password,
                        firstName = formState.firstName,
                        lastName = formState.lastName,
                        profilePictureId = null
                    )
                ).fold(
                    onSuccess = {
                        formState = formState.copy(isLoading = false)
                    },
                    onFailure = {
                        formState = formState.copy(isLoading = false)
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