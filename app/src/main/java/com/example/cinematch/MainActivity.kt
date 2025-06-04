package com.example.cinematch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cinematch.core.navigation.MainScreenWithBottomNav
import com.example.cinematch.core.navigation.Screen
import com.example.cinematch.core.ui.theme.AppTheme
import com.example.cinematch.features.auth.ui.LoginScreen
import com.example.cinematch.features.auth.ui.SignUpScreen
import com.example.cinematch.features.profile.ui.ProfileCreationScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.ExperimentalSerializationApi

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalSerializationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Screen.SignUp.route) {
                    composable(Screen.SignUp.route) {
                        SignUpScreen(
                            onSignUpClick = { navController.navigate(Screen.Login.route) },
                            onSuccessSignUp = {
                                navController.navigate(Screen.Main.route) {
                                    popUpTo(Screen.SignUp.route) { inclusive = true }
                                }
                            }
                        )
                    }

                    composable(Screen.Login.route) {
                        LoginScreen()
                    }

                    composable(Screen.Main.route) {
                        MainScreenWithBottomNav()
                    }

                    composable(Screen.ProfileCreation.route) {
                        ProfileCreationScreen()
                    }
                }
            }
        }
    }
}