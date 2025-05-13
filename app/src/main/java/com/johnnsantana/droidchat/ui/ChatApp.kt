package com.johnnsantana.droidchat.ui

import android.annotation.SuppressLint

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.johnnsantana.droidchat.navigation.ChatNavHost
import com.johnnsantana.droidchat.navigation.rememberDroidChatNavigationState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatApp() {
    val navigationState = rememberDroidChatNavigationState()
    Scaffold(
        bottomBar = {}
    ) {
        Box(
            modifier = Modifier
                .imePadding()
                .fillMaxSize()
        ) {
            ChatNavHost(
                navigationState = navigationState
            )
        }

    }
}