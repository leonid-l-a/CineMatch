package com.example.cinematch.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cinematch.features.friends.ui.FriendsScreen
import com.example.cinematch.features.movies.ui.MoviesScreen
import com.example.cinematch.features.profile.ui.ProfileScreen

@Composable
fun MainScreenWithBottomNav() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Friends.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Friends.route) { FriendsScreen() }
            composable(Screen.Movies.route) { MoviesScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}
