package com.johnnsantana.droidchat.ui.feature.chats

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.johnnsantana.droidchat.navigation.Route

fun NavController.navigateToChats(
    navOptions: NavOptions? = null
) {
    this.navigate(Route.ChatsRoute, navOptions)
}