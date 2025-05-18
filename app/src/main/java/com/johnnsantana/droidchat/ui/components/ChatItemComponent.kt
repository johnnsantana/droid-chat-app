package com.johnnsantana.droidchat.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import coil.compose.AsyncImage
import com.johnnsantana.droidchat.R
import com.johnnsantana.droidchat.model.Chat
import com.johnnsantana.droidchat.ui.preview.ChatPreviewParameterProvider
import com.johnnsantana.droidchat.ui.theme.DroidChatTheme

@Composable
fun ChatItemComponent(
    chat: Chat,
    modifier: Modifier = Modifier
) {
    val receiver = remember(chat.members) {
        chat.members.first { it.self.not() }
    }


    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        val (
            avatarRef,
            firstNameRef,
            lastMessageRef,
            lastMessageTimeRef,
            unreadCountRef
        ) = createRefs()


        AsyncImage(
            model = receiver.profilePictureUrl,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(60.dp)
                .constrainAs(avatarRef) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
            placeholder = painterResource(R.drawable.no_profile_image),
            error = painterResource(R.drawable.no_profile_image),
            fallback = painterResource(R.drawable.no_profile_image)
        )

        Text(
            text = receiver.firstName,
            modifier = Modifier.constrainAs(firstNameRef) {
                top.linkTo(avatarRef.top)
                start.linkTo(avatarRef.end, margin = 16.dp)
                end.linkTo(lastMessageTimeRef.start, margin = 16.dp)
                bottom.linkTo(lastMessageRef.top)
                width = Dimension.fillToConstraints
            },
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = receiver.lastName,
            modifier = Modifier.constrainAs(lastMessageRef) {
                top.linkTo(firstNameRef.bottom)
                start.linkTo(avatarRef.end, margin = 16.dp)
                end.linkTo(unreadCountRef.start, margin = 16.dp)
                bottom.linkTo(firstNameRef.bottom)
                width = Dimension.fillToConstraints
            },
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = chat.timestamp,
            modifier = Modifier.constrainAs(lastMessageTimeRef) {
                top.linkTo(firstNameRef.top)
                end.linkTo(parent.end)
                bottom.linkTo(unreadCountRef.top)
                width = Dimension.wrapContent
            },
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.bodySmall,
        )


        Text(
            text = chat.unreadCount.toString(),
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(horizontal = 4.dp)
                .constrainAs(unreadCountRef) {
                top.linkTo(lastMessageTimeRef.bottom)
                end.linkTo(parent.end)
                bottom.linkTo(lastMessageRef.bottom)
                width = Dimension.wrapContent
                    visibility = if (chat.unreadCount > 0) {
                        Visibility.Visible
                    } else Visibility.Gone
            },
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.bodySmall,
        )

    }
}


@Preview
@Composable
private fun ChatItemComponentPreview(
    @PreviewParameter(ChatPreviewParameterProvider::class)
    chat: Chat
) {
    DroidChatTheme {
        ChatItemComponent(
            chat = chat
        )
    }
}