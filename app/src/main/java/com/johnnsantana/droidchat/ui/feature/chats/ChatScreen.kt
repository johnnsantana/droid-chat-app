package com.johnnsantana.droidchat.ui.feature.chats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.johnnsantana.droidchat.R
import com.johnnsantana.droidchat.model.Chat
import com.johnnsantana.droidchat.ui.components.ChatItemComponent
import com.johnnsantana.droidchat.ui.preview.ChatListPreviewParameterProvider
import com.johnnsantana.droidchat.ui.theme.DroidChatTheme
import com.johnnsantana.droidchat.ui.theme.Grey1

@Composable
fun ChatsRoute(
    viewModel: ChatsViewModel = hiltViewModel()
) {
    val chatsListUIState by viewModel.chatListUIState.collectAsStateWithLifecycle()
    ChatsScreen(
        chatsListUIState = chatsListUIState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatsScreen(
    chatsListUIState: ChatsViewModel.ChatsListUIState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = AnnotatedString.fromHtml(
                            stringResource(
                                R.string.feature_chats_greeting,
                                "John"
                            )
                        ),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                expandedHeight = 100.dp
            )
        },
        containerColor = MaterialTheme.colorScheme.primary,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.extraLarge.copy(
                        bottomStart = CornerSize(0.dp),
                        bottomEnd = CornerSize(0.dp)
                    )
                )
                .clip(
                    shape = MaterialTheme.shapes.extraLarge.copy(
                        bottomStart = CornerSize(0.dp),
                        bottomEnd = CornerSize(0.dp)
                    )
                )
                .fillMaxSize(),
        ) {
            when (chatsListUIState) {
                ChatsViewModel.ChatsListUIState.Loading -> {

                }
                is ChatsViewModel.ChatsListUIState.Success -> {
                    ChatsListContent(chats = chatsListUIState.chats)
                }
                ChatsViewModel.ChatsListUIState.Error -> {

                }
            }
        }
    }
}

@Composable
fun ChatsListContent(chats: List<Chat>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(chats) { index, chat ->
            ChatItemComponent(chat)
            if (index < chats.lastIndex) {
                HorizontalDivider(
                    color = Grey1,
                )
            }
        }
    }
}


@Preview
@Composable
private fun ChatsScreenLoadingPreview() {
    DroidChatTheme {
        ChatsScreen(
            chatsListUIState = ChatsViewModel.ChatsListUIState.Loading
        )
    }
}

@Preview
@Composable
private fun ChatsScreenSuccessPreview(
    @PreviewParameter(ChatListPreviewParameterProvider::class)
    chats: List<Chat>
) {
    DroidChatTheme {
        ChatsScreen(
            chatsListUIState = ChatsViewModel.ChatsListUIState.Success(
                chats = chats
            )
        )
    }
}

@Preview
@Composable
private fun ChatsScreenErrorPreview() {
    DroidChatTheme {
        ChatsScreen(
            chatsListUIState = ChatsViewModel.ChatsListUIState.Error
        )
    }
}