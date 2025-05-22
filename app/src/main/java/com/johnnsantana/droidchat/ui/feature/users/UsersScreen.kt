package com.johnnsantana.droidchat.ui.feature.users

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.johnnsantana.droidchat.R
import com.johnnsantana.droidchat.model.User
import com.johnnsantana.droidchat.model.fake.user2
import com.johnnsantana.droidchat.model.fake.user3
import com.johnnsantana.droidchat.model.fake.user4
import com.johnnsantana.droidchat.ui.components.AnimatedContent
import com.johnnsantana.droidchat.ui.components.ChatScaffold
import com.johnnsantana.droidchat.ui.components.ChatTopAppBar
import com.johnnsantana.droidchat.ui.components.GeneralError
import com.johnnsantana.droidchat.ui.components.PrimaryButtonComponent
import com.johnnsantana.droidchat.ui.components.UserItem
import com.johnnsantana.droidchat.ui.theme.DroidChatTheme
import com.johnnsantana.droidchat.ui.theme.Grey1
import kotlinx.coroutines.flow.flowOf

@Composable
fun UsersRoute(
    viewModel: UsersViewModel = hiltViewModel(),
    navigateToChatDetails: (userId: Int) -> Unit
) {
    val pagingUsers = viewModel.usersFlow.collectAsLazyPagingItems()
    UsersScreen(
        pagingUsers = pagingUsers,
        onUserClicked = navigateToChatDetails
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(
    pagingUsers: LazyPagingItems<User>,
    onUserClicked: (userId: Int) -> Unit
) {
    ChatScaffold(
        topBar = {
            ChatTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.feature_users_title))
                }
            )
        }
    ) {
        when (pagingUsers.loadState.refresh) {
            is LoadState.Loading -> {
                CircularProgressIndicator(
                    Modifier.align(Alignment.Center)
                )
            }
            is LoadState.NotLoading -> {
                LazyColumn(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(pagingUsers.itemCount) { index ->
                        val user = pagingUsers[index]
                        if (user != null) {
                            UserItem(
                                user,
                                modifier = Modifier.clickable {
                                    onUserClicked(user.id)
                                }
                            )

                            if (index < pagingUsers.itemCount - 1) {
                                HorizontalDivider(
                                    modifier = Modifier.padding(top = 16.dp),
                                    color = Grey1
                                )
                            }
                        }
                    }

                    if (pagingUsers.loadState.append is LoadState.Loading) {
                        item {
                            Box(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    if (pagingUsers.loadState.append is LoadState.Error) {
                        item {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = stringResource(R.string.feature_users_error_loading_more),
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    color = MaterialTheme.colorScheme.error
                                )

                                PrimaryButtonComponent(
                                    text = stringResource(R.string.common_retry_again),
                                    onClick = {
                                        pagingUsers.retry()
                                    },
                                    modifier = Modifier
                                        .padding(horizontal = 30.dp)
                                        .height(46.dp)
                                )
                            }
                        }
                    }
                }
            }
            is LoadState.Error -> {
                GeneralError(
                    title = stringResource(id = R.string.common_generic_error_title),
                    message = stringResource(id = R.string.common_generic_error_message),
                    resource = {
                        AnimatedContent(
                            resId = R.raw.animation_generic_error
                        )
                    },
                    action = {
                        PrimaryButtonComponent(
                            text = stringResource(id = R.string.common_retry_again),
                            onClick = {
                                pagingUsers.retry()
                            }
                        )
                    }
                )
            }
        }

    }
}

@Preview
@Composable
private fun UsersScreenPreview() {
    DroidChatTheme {
        val usersFlow = flowOf(
            PagingData.from(
            listOf(
                user2, user3, user4
            ), sourceLoadStates = LoadStates (
                    refresh = LoadState.Error(Throwable()),
                prepend = LoadState.NotLoading(false),
                append = LoadState.NotLoading(false)
                )
            )
        )
        UsersScreen(
            pagingUsers = usersFlow.collectAsLazyPagingItems(),
            onUserClicked = {}
        )
    }
}