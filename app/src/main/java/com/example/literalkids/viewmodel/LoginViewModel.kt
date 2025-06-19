package com.example.literalkids.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.literalkids.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoginSuccessful: Boolean = false,
    val token: String? = null
)

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val authRepository = AuthRepository(application.applicationContext)
    
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        // Clear error when user starts typing
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        // Clear error when user starts typing
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    fun login(onSuccess: () -> Unit) {
        if (_email.value.isBlank()) {
            _uiState.value = _uiState.value.copy(errorMessage = "Email tidak boleh kosong")
            return
        }
        
        if (_password.value.isBlank()) {
            _uiState.value = _uiState.value.copy(errorMessage = "Password tidak boleh kosong")
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            
            authRepository.login(_email.value, _password.value)
                .onSuccess { loginResponse ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isLoginSuccessful = true,
                        token = loginResponse.token
                    )
                    // Simpan token ke SharedPreferences atau DataStore di sini jika diperlukan
                    onSuccess()
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Login gagal"
                    )
                }
        }
    }

    fun clearErrorMessage() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}