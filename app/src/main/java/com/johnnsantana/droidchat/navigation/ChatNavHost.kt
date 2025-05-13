package com.johnnsantana.droidchat.navigation

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.johnnsantana.droidchat.navigation.extension.slideInTo
import com.johnnsantana.droidchat.navigation.extension.slideOutTo
import com.johnnsantana.droidchat.ui.feature.chats.ChatsRoute
import com.johnnsantana.droidchat.ui.feature.chats.navigateToChats
import com.johnnsantana.droidchat.ui.feature.signin.SignInRoute
import com.johnnsantana.droidchat.ui.feature.signup.SignUpRoute
import com.johnnsantana.droidchat.ui.feature.splash.SplashRoute



@SuppressLint("ContextCastToActivity")
@Composable
fun ChatNavHost() {
    val navController = rememberNavController()
    val activity = LocalContext.current as? Activity

    NavHost(navController = navController, startDestination = Route.SplashRoute) {
        composable<Route.SplashRoute> {
            SplashRoute(
                onNavigateToSignIn = {
                    navController.navigate(
                        route = Route.SignInRoute,
                        navOptions = navOptions {
                            popUpTo(Route.SplashRoute) {
                                inclusive = true
                            }
                        }
                    )
                },
                onNavigateToMain = {
                    navController.navigateToChats(
                        navOptions = navOptions {
                            popUpTo(Route.SplashRoute) {
                                inclusive = true
                            }
                        }
                    )
                },
                onCloseApp = {
                    activity?.finish()
                }
            )
        }
        composable<Route.SignInRoute>(
            enterTransition = {
               this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Right)
            },
            exitTransition = {
                this.slideOutTo(AnimatedContentTransitionScope.SlideDirection.Left)
            }
        ) {
            SignInRoute(
                navigateToSignUp = {
                    navController.navigate(Route.SignUpRoute)
                },
                onNavigateToMain = {
                    navController.navigateToChats(
                        navOptions = navOptions {
                            popUpTo(Route.SplashRoute) {
                                inclusive = true
                            }
                        }
                    )
                },
            )
        }
        composable<Route.SignUpRoute>(
            enterTransition = {
                this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Left)
            },
            exitTransition = {
                this.slideOutTo(AnimatedContentTransitionScope.SlideDirection.Right)
            }
        ) {
            SignUpRoute(
                onSignUpSuccess = {
                    navController.popBackStack()
                },
            )
        }

        composable<Route.ChatsRoute> {
            ChatsRoute()
        }
    }
}
