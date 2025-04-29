package com.johnnsantana.droidchat.ui.feature.sigin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.johnnsantana.droidchat.R
import com.johnnsantana.droidchat.ui.theme.BackgroundGradient
import com.johnnsantana.droidchat.ui.theme.DroidChatTheme

@Composable
fun SignInRoute() {
    SigInScreen()
}

@Composable
fun SigInScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = BackgroundGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )


    }
}

@Preview
@Composable
private fun SignInScreenPreview() {
    DroidChatTheme {
        SigInScreen()
    }
}