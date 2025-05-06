package com.johnnsantana.droidchat.ui.feature.signup

import android.net.Uri
import androidx.annotation.StringRes

data class SignUpFormState (
   val profilePictureUri: Uri? = null,
   val firstName: String = "",
   @StringRes
   val firstNameErrorMessage: Int? = null,
   val lastName: String = "",
   @StringRes
   val lastNameErrorMessage: Int? = null,
   val email: String = "",
   @StringRes
   val emailErrorMessage: Int? = null,
   val password: String = "",
   @StringRes
   val passwordErrorMessage: Int? = null,
   val passwordConfirmation: String = "",
   @StringRes
   val passwordConfirmationError: Int? = null,
   @StringRes
   val passwordExtraText: Int? = null,
   val isProfilePictureModalBottomSheetOpen: Boolean = false,
   val hasError: Boolean = false,
   val isLoading: Boolean = false
)
