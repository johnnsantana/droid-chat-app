package com.johnnsantana.droidchat.ui.feature.signup

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.johnnsantana.droidchat.R
import com.johnnsantana.droidchat.ui.components.PrimaryButtonComponent
import com.johnnsantana.droidchat.ui.components.ProfilePictureSelectorComponent
import com.johnnsantana.droidchat.ui.components.SecondaryTextFieldComponent
import com.johnnsantana.droidchat.ui.theme.BackgroundGradient
import com.johnnsantana.droidchat.ui.theme.DroidChatTheme

@Composable
fun SignUpRoute() {
    SignUpScreen()
}

@Composable
fun SignUpScreen() {
    Box(
        modifier = Modifier
            .background(brush = BackgroundGradient)
            .verticalScroll(state = rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var profilePictureSelectedUri by remember {
                mutableStateOf<Uri?>(null)
            }

            Spacer(modifier = Modifier.height(56.dp))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                shape = MaterialTheme.shapes.extraLarge.copy(
                    bottomEnd = CornerSize(0.dp),
                    bottomStart = CornerSize(0.dp)
                ),
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                    ProfilePictureSelectorComponent(
                        imageUri = profilePictureSelectedUri
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    SecondaryTextFieldComponent(
                        label = stringResource(R.string.feature_sign_up_first_name),
                        value = "",
                        onValueChange = {},
                    )
                    Spacer(modifier = Modifier.height(22.dp))

                    SecondaryTextFieldComponent(
                        label = stringResource(R.string.feature_sign_up_last_name),
                        value = "",
                        onValueChange = {},
                    )
                    Spacer(modifier = Modifier.height(22.dp))

                    SecondaryTextFieldComponent(
                        label = stringResource(R.string.feature_sign_up_email),
                        value = "",
                        onValueChange = {},
                        keyboardType = KeyboardType.Email
                    )
                    Spacer(modifier = Modifier.height(22.dp))

                    SecondaryTextFieldComponent(
                        label = stringResource(R.string.feature_sign_up_password),
                        value = "",
                        onValueChange = {}
                    )
                    Spacer(modifier = Modifier.height(22.dp))

                    SecondaryTextFieldComponent(
                        label = stringResource(R.string.feature_sign_up_password_confirmation),
                        value = "",
                        onValueChange = {},
                        imeAction = ImeAction.Done
                    )
                    Spacer(modifier = Modifier.height(36.dp))

                    PrimaryButtonComponent(
                        text = stringResource(R.string.feature_sign_up_button),
                        modifier = Modifier,
                        onClick = {}
                    )

                }
            }
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    DroidChatTheme {
        SignUpScreen()
    }
}