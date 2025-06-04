package com.example.cinematch.features.auth.presentation

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinematch.features.auth.data.NetworkResponse
import com.example.cinematch.features.auth.domain.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState

    fun onIntent(intent: SignUpIntent) {
        when (intent) {
            is SignUpIntent.EmailChanged -> {
                _uiState.update { state ->
                    val error = when {
                        intent.email.isBlank() -> null
                        !Patterns.EMAIL_ADDRESS.matcher(intent.email).matches() -> "Invalid email"
                        else -> null
                    }
                    state.copy(email = intent.email, emailError = error)
                }
                validateForm()
            }

            is SignUpIntent.PasswordChanged -> {
                _uiState.update { state ->
                    val error = when {
                        intent.password.isBlank() -> null
                        intent.password.length < 6 -> "Password must be at least 6 characters"
                        else -> null
                    }
                    state.copy(password = intent.password, passwordError = error)
                }
                validateForm()
            }

            is SignUpIntent.RepeatPasswordChanged -> {
                _uiState.update { state ->
                    val error = when {
                        intent.password.isBlank() -> null
                        intent.password != state.password -> "Passwords do not match"
                        else -> null
                    }
                    state.copy(repeatPassword = intent.password, repeatPasswordError = error)
                }
                validateForm()
            }

            SignUpIntent.SignUpClicked -> {
                if (_uiState.value.isFormValid) {
                    _uiState.update { it.copy(signUpError = null) }
                    viewModelScope.launch {
                        when (val result = signUpUseCase.signUp(
                            email = _uiState.value.email,
                            password = _uiState.value.password
                        )) {
                            is NetworkResponse.Success -> {
                                _uiState.update { it.copy(signUpSuccessful = true) }
                            }

                            is NetworkResponse.Error -> {
                                _uiState.update { it.copy(signUpError = result.message) }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun validateForm() {
        _uiState.update { state ->
            val isEmailValid = state.email.isNotBlank() && state.emailError == null
            val isPasswordValid = state.password.isNotBlank() && state.passwordError == null
            val isRepeatValid =
                state.repeatPassword.isNotBlank() && state.repeatPasswordError == null
            state.copy(isFormValid = isEmailValid && isPasswordValid && isRepeatValid)
        }
    }
}