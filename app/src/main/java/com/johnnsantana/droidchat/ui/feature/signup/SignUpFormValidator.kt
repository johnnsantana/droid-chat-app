package com.johnnsantana.droidchat.ui.feature.signup

import com.johnnsantana.droidchat.R
import com.johnnsantana.droidchat.validator.EmailValidator
import com.johnnsantana.droidchat.validator.FormValidator
import com.johnnsantana.droidchat.validator.PasswordValidator
import javax.inject.Inject

class SignUpFormValidator @Inject constructor() : FormValidator<SignUpFormState> {

    override fun validate(formState: SignUpFormState): SignUpFormState {
        val isFirstNameValid = formState.firstName.isNotEmpty()
        val isLastNameValid = formState.lastName.isNotEmpty()
        val isEmailValid = EmailValidator.isValidEmail(formState.email)
        val isPasswordValid = PasswordValidator.isValid(formState.password)
        val isPasswordConfirmationValid = PasswordValidator.isValid(formState.passwordConfirmation)
                && formState.passwordConfirmation == formState.password

        val hasError = listOf(
            isFirstNameValid,
            isLastNameValid,
            isEmailValid,
            isPasswordValid,
            isPasswordConfirmationValid,
        ).any { !it }

        return formState.copy(
            firstNameErrorMessage = if (!isFirstNameValid) R.string.error_message_field_blank else null,
            lastNameErrorMessage = if (!isLastNameValid) R.string.error_message_field_blank else null,
            emailErrorMessage = if (!isEmailValid) R.string.error_message_email_invalid else null,
            passwordErrorMessage = if (!isPasswordValid) R.string.error_message_password_invalid else null,
            passwordConfirmationError = if (!isPasswordConfirmationValid) R.string.error_message_password_confirmation_invalid else null,
            hasError = hasError,
        )
    }
}