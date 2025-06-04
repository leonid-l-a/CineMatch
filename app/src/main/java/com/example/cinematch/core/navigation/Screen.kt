package com.example.cinematch.core.navigation

sealed class Screen(val route: String) {
    object SignUp : Screen("sign_up")
    object Login : Screen("login")
    object ProfileCreation : Screen("profile_creation")

    object Main : Screen("main")
    object Movies : Screen("movies")
    object Friends : Screen("friends")
    object Profile : Screen("profile")

}
