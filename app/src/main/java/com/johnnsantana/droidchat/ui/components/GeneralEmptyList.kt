package com.johnnsantana.droidchat.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.johnnsantana.droidchat.ui.theme.DroidChatTheme

@Composable
fun GeneralEmptyList(
    message: String,
    modifier: Modifier = Modifier,
    resource: (@Composable () -> Unit)? = null,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        resource?.let {
            Box(
                modifier = Modifier
                    .sizeIn(
                        maxHeight = 200.dp,
                        maxWidth = 200.dp
                    )
            ) {
                it()
            }
            Spacer(modifier = Modifier.height(32.dp))
        }


        Text(
            text = message,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge,
        )

    }

}


@Preview
@Composable

private fun GeneralEmptyListPreview() {
    DroidChatTheme {
        GeneralEmptyList(
            message = "Lista vazia",

        )
    }
}