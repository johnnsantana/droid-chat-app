package com.johnnsantana.droidchat.ui.di

import com.johnnsantana.droidchat.ui.feature.signup.SignUpFormState
import com.johnnsantana.droidchat.ui.feature.signup.SignUpFormValidator
import com.johnnsantana.droidchat.validator.FormValidator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface FormValidatorModule {

    @Binds
    fun bindSignUpFormValidator(
        signUpFormValidator: SignUpFormValidator
    ): FormValidator<SignUpFormState>

}
