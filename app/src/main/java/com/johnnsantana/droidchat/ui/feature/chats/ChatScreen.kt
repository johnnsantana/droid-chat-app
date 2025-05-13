package com.johnnsantana.droidchat.ui.feature.chats

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.johnnsantana.droidchat.ui.components.ChatItemComponent
import com.johnnsantana.droidchat.ui.theme.DroidChatTheme
import com.johnnsantana.droidchat.ui.theme.Grey1

@Composable
fun ChatsRoute() {
    ChatsScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Olá, John!")
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items( 100) {
                ChatItemComponent()
                HorizontalDivider(
                    color = Grey1,
                )
            }
        }
    }
}

@Preview
@Composable
private fun ChatsScreenPreview() {
    DroidChatTheme {
        ChatsScreen()
    }
}