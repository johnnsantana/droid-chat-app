package com.johnnsantana.droidchat.ui

import android.annotation.SuppressLint

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.johnnsantana.droidchat.navigation.ChatNavHost
import com.johnnsantana.droidchat.navigation.DroidChatNavigationState
import com.johnnsantana.droidchat.navigation.rememberDroidChatNavigationState
import com.johnnsantana.droidchat.ui.components.BottomNavigationMenuComponent
import com.johnnsantana.droidchat.ui.theme.Grey1

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatApp(
    navigationState: DroidChatNavigationState = rememberDroidChatNavigationState()
) {
    val topLevelDestinations = remember(navigationState.topLevelDestinations) {
        navigationState.topLevelDestinations.toSet()
    }

    Scaffold(
        bottomBar = {
            if (navigationState.currentTopLevelDestination in topLevelDestinations) {
                BottomNavigationMenuComponent(
                    navigationState = navigationState
                )
            }
        },
        contentColor = Grey1,
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