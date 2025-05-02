package com.johnnsantana.droidchat.ui.extension

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

fun KeyboardType.getVisualTransformationForPassword(isPasswordVisible: Boolean)
    : VisualTransformation {

    return if (this == KeyboardType.Password) {
        if(isPasswordVisible) {
            VisualTransformation.None
        } else PasswordVisualTransformation()
    } else VisualTransformation.None

}