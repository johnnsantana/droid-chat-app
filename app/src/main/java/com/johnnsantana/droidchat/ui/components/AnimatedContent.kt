package com.johnnsantana.droidchat.ui.components

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.johnnsantana.droidchat.R
import com.johnnsantana.droidchat.ui.theme.DroidChatTheme

@Composable
fun AnimatedContent(
    modifier: Modifier = Modifier,
    @RawRes resId: Int
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(resId)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}

@Preview
@Composable
private fun AnimatedContentPreview() {
    DroidChatTheme {
        AnimatedContent(
            resId = R.raw.animation_generic_error
        )
    }
}