package com.example.cinematch.features.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cinematch.features.auth.presentation.SignUpIntent
import com.example.cinematch.features.auth.presentation.SignUpViewModel

@Composable
fun SignUpScreen(
    onSignUpClick: () -> Unit,
    onSuccessSignUp: () -> Unit,
) {

    val viewModel: SignUpViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(uiState.signUpSuccessful) {
        if (uiState.signUpSuccessful) {
            onSuccessSignUp()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            OutlinedTextField(
                value = uiState.email,
                onValueChange = { viewModel.onIntent(SignUpIntent.EmailChanged(it)) },
                shape = RoundedCornerShape(15.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeholder = {
                    Text(
                        text = "example@gmail.com",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                singleLine = true,
                label = { Text("Email") },
                isError = uiState.emailError != null,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                )
            )
            val emailError = uiState.emailError
            if (emailError != null) {
                Text(
                    text = emailError,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            OutlinedTextField(
                value = uiState.password,
                onValueChange = { viewModel.onIntent(SignUpIntent.PasswordChanged(it)) },
                shape = RoundedCornerShape(15.dp),
                singleLine = true,
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                isError = uiState.passwordError != null,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                )
            )
            val passwordError = uiState.passwordError
            if (passwordError != null) {
                Text(
                    text = passwordError,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            OutlinedTextField(
                value = uiState.repeatPassword,
                onValueChange = { viewModel.onIntent(SignUpIntent.RepeatPasswordChanged(it)) },
                shape = RoundedCornerShape(15.dp),
                singleLine = true,
                label = { Text("Repeat password") },
                visualTransformation = PasswordVisualTransformation(),
                isError = uiState.repeatPasswordError != null,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                )
            )

            val repeatPasswordError = uiState.repeatPasswordError
            if (repeatPasswordError != null) {
                Text(
                    text = repeatPasswordError,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Button(
                onClick = { viewModel.onIntent(SignUpIntent.SignUpClicked) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green.copy(alpha = 0.2f)
                ),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(vertical = 8.dp),
                enabled = uiState.isFormValid
            ) {
                Text(
                    text = "Sign up",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Button(
            onClick = onSignUpClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
            ),
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
        ) {
            Text(
                text = "Already have an account? Log in",
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Composable
@Preview
private fun Preview() {
    SignUpScreen({}, {})
}