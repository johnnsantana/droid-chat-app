package com.johnnsantana.droidchat.ui.feature.signin

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.johnnsantana.droidchat.R
import com.johnnsantana.droidchat.ui.components.AlertDialogComponent
import com.johnnsantana.droidchat.ui.components.PrimaryButtonComponent
import com.johnnsantana.droidchat.ui.components.PrimaryTextFieldComponent
import com.johnnsantana.droidchat.ui.theme.BackgroundGradient
import com.johnnsantana.droidchat.ui.theme.DroidChatTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignInRoute(
    viewModel: SigInViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
    navigateToSignUp: () -> Unit
) {
    var formState = viewModel.formState

    val genericErrorMessage = stringResource(id = R.string.common_generic_error_message)
    var showUnauthorizedError by remember { mutableStateOf(false) }
    LaunchedEffect(true) {
        viewModel.signInActionFlow.collectLatest { action ->
            when(action) {
                is SignInAction.Success -> {

                }
                is SignInAction.Error -> {
                    when(action) {
                        SignInAction.Error.GenericError -> {
                            Toast.makeText(
                                context,
                                genericErrorMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        SignInAction.Error.UnauthorizedError -> {
                            showUnauthorizedError = true
                        }
                    }
                }
            }

        }
    }

    SigInScreen(
        formState = formState,
        onFormEvent = viewModel::onEvent,
        onRegisterClick = navigateToSignUp
    )

    if (showUnauthorizedError) {
        AlertDialogComponent(
            onDismissRequest = {
                showUnauthorizedError = false
            },
            onConfirmButtonClick = {
                showUnauthorizedError = false
            },
            title = stringResource(id = R.string.common_generic_error_title),
            message = stringResource(id = R.string.error_message_invalid_username_or_password)
        )
    }
}

@Composable
fun SigInScreen(
    formState: SignInFormState,
    onFormEvent: (SignInFormEvent) -> Unit = {},
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(brush = BackgroundGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )
        Spacer(Modifier.height(78.dp))

        PrimaryTextFieldComponent(
            value = formState.email,
            onValueChange = {
                onFormEvent(SignInFormEvent.EmailChanged(it))
            },
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.spacing_medium)),
            placeholder = stringResource(R.string.feature_login_email),
            leadingIcon = R.drawable.ic_envelope,
            keyboardType = KeyboardType.Email,
            errorMessage = formState.emailError?.let {
                stringResource(id = it)
            }
        )

        Spacer(Modifier.height(16.dp))

        PrimaryTextFieldComponent(
            value = formState.password,
            onValueChange = {
                onFormEvent(SignInFormEvent.PasswordChanged(it))
            },
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.spacing_medium)),
            placeholder = stringResource(R.string.feature_login_password),
            leadingIcon = R.drawable.ic_lock,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            errorMessage = formState.passwordError?.let {
                stringResource(id = it)
            }
        )

        Spacer(Modifier.height(98.dp))

        PrimaryButtonComponent(
            text = stringResource(id = R.string.feature_login_button),
            onClick = {
                onFormEvent(SignInFormEvent.Submit)
            },
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.spacing_medium)),
            isLoading = formState.isLoading

        )

        Spacer(Modifier.height(56.dp))

        val noAccountLabel = stringResource(id = R.string.feature_login_no_account)
        val registerLabel = stringResource(id = R.string.feature_login_register)
        val annotatedString = buildAnnotatedString {
            val text = "$noAccountLabel $registerLabel"
            val startIndex = text.indexOf(registerLabel)
            val endIndex = startIndex + registerLabel.lastIndex + 1

            append(text)
            addStyle(
                style = SpanStyle(
                    color = Color.White,
                ),
                start = 0,
                end = startIndex
            )
            addStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                ),
                start = startIndex,
                end = endIndex
            )

            addLink(
                clickable = LinkAnnotation.Clickable(
                    tag = "register,",
                    linkInteractionListener = {
                        onRegisterClick()
                    }
                ),
                start = startIndex,
                end = endIndex
            )
        }

        Text(text = annotatedString)
    }
}

@Preview
@Composable
private fun SignInScreenPreview() {
    DroidChatTheme {
        SigInScreen(
            formState = SignInFormState(),
            onRegisterClick = {}
        )
    }
}
