package com.johnnsantana.droidchat.ui.feature.signin

import com.johnnsantana.droidchat.R
import com.johnnsantana.droidchat.validator.EmailValidator
import com.johnnsantana.droidchat.validator.FormValidator
import com.johnnsantana.droidchat.validator.PasswordValidator
import javax.inject.Inject

class SignInFormValidator @Inject constructor() : FormValidator<SignInFormState> {

    override fun validate(formState: SignInFormState): SignInFormState {
        val isEmailValid = EmailValidator.isValidEmail(formState.email)
        val isPasswordValid = PasswordValidator.isValid(formState.password)

        val hasError = listOf(
            isEmailValid,
            isPasswordValid,
        ).any { !it }

        return formState.copy(
            emailError = if (!isEmailValid) R.string.error_message_email_invalid else null,
            passwordError = if (!isPasswordValid) R.string.error_message_password_invalid else null,
            hasError = hasError,
        )
    }
}