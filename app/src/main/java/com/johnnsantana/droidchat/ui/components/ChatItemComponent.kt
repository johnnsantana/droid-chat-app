package com.johnnsantana.droidchat.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.johnnsantana.droidchat.R
import com.johnnsantana.droidchat.ui.theme.DroidChatTheme

@Composable
fun ChatItemComponent(
    modifier: Modifier = Modifier
) {

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


        Image(
            painter = painterResource(id = R.drawable.no_profile_image),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(60.dp)
                .constrainAs(avatarRef) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = "John Doe",
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
            text = "last message",
            modifier = Modifier.constrainAs(lastMessageRef) {
                top.linkTo(firstNameRef.bottom)
                start.linkTo(avatarRef.end, margin = 16.dp)
                end.linkTo(unreadCountRef.start, margin = 16.dp)
                bottom.linkTo(avatarRef.bottom)
                width = Dimension.fillToConstraints
            },
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = "13:45",
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
            text = "2",
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(horizontal = 4.dp)
                .constrainAs(unreadCountRef) {
                top.linkTo(lastMessageTimeRef.bottom)
                end.linkTo(parent.end)
                bottom.linkTo(lastMessageRef.bottom)
                width = Dimension.wrapContent
            },
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.bodySmall,
        )

    }
}

@Preview
@Composable
private fun ChatItemComponentPreview() {
    DroidChatTheme {
        ChatItemComponent()
    }
}