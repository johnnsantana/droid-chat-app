package com.johnnsantana.droidchat.ui.components

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.johnnsantana.droidchat.R
import com.johnnsantana.droidchat.ui.theme.DroidChatTheme

@Composable
fun ProfilePictureSelectorComponent(
    modifier: Modifier = Modifier,
    imageUri: Uri? = null
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = imageUri ?: R.drawable.ic_upload_photo,
            contentDescription = null,
            modifier = modifier
                .size(84.dp)
                .clip(CircleShape),
            placeholder = painterResource(R.drawable.ic_upload_photo),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(id = R.string.common_add_profile_photo),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePictureSelectorComponentPreview() {
    DroidChatTheme {
        ProfilePictureSelectorComponent()
    }
}