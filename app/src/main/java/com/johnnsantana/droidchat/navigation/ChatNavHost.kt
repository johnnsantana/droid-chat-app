package com.johnnsantana.droidchat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

const val SPLASH_ROUTE = "splash"
const val SIGN_IN_ROUTE = "signIn"
const val SIGN_UP_ROUTE = "signUp"

@Composable
fun ChatNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SPLASH_ROUTE) {
        composable(route = SPLASH_ROUTE) {}
        composable(route = SIGN_IN_ROUTE) {}
        composable(route = SIGN_UP_ROUTE) {}
    }
}
