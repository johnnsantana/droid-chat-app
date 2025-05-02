package com.johnnsantana.droidchat.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.johnnsantana.droidchat.ui.theme.DroidChatTheme

@Composable
fun PrimaryButtonComponent(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    var isLoading by remember {
        mutableStateOf(false)
    }

    Button(
        onClick = {
            onClick()
            isLoading = !isLoading
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
        ,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(30.dp)
                    .aspectRatio(1f),
                color = MaterialTheme.colorScheme.onPrimary,
                strokeCap = StrokeCap.Round,

            )
        } else {
            Text(
                text = text,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

    }
}

@Preview
@Composable
private fun PrimaryButtonComponentPreview() {
    DroidChatTheme {
        PrimaryButtonComponent(
            text = "Sign In",
            onClick = {}
        )
    }
}