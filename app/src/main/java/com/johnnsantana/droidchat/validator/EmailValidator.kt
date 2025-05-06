package com.johnnsantana.droidchat.validator

object EmailValidator {
    const val EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"

    fun isValidEmail(email: String): Boolean {
        return Regex(EMAIL_REGEX).matches(email)
    }
}