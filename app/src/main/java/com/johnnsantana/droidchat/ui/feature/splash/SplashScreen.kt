package com.johnnsantana.droidchat.ui.feature.splash

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleStartEffect
import com.johnnsantana.droidchat.R
import com.johnnsantana.droidchat.ui.theme.BackgroundGradient
import com.johnnsantana.droidchat.ui.theme.DroidChatTheme


@Composable
fun SplashRoute(
    viewModel: SplashViewModel = hiltViewModel(),
    onNavigateToSignIn: () -> Unit,
    onNavigateToMain: () -> Unit,
    onCloseApp: () -> Unit
) {
    SplashScreen()

    LifecycleStartEffect(Unit) {
        viewModel.checkSession()
        onStopOrDispose {  }
    }

    LaunchedEffect(Unit) {
        viewModel.authenticationState.collect { authenticationState ->
            when (authenticationState) {
                SplashViewModel.AuthenticationState.UserAuthenticated -> {
                    onNavigateToMain()
                }
                SplashViewModel.AuthenticationState.UserNotAuthenticated -> {
                    onNavigateToSignIn()
                }
            }

        }
    }
}

@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = BackgroundGradient)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo), contentDescription = null
        )

        Spacer(modifier = Modifier.height(77.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_safety),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = stringResource(R.string.splash_safety_info),
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    DroidChatTheme {
        SplashScreen(

        )
    }
}