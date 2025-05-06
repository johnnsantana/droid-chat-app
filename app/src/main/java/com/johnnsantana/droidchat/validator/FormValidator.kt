package com.johnnsantana.droidchat.validator

interface FormValidator<FormState> {
    fun validate(formState: FormState): FormState
}