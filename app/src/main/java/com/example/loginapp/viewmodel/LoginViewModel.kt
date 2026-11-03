package com.example.loginapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.loginapp.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/** Status proses login yang diamati oleh UI. */
sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data class Success(val username: String) : LoginState()
    data class Error(val message: String) : LoginState()
}

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    init {
        insertDummyUser()
    }

    fun login(username: String, password: String) {
        // 1. Validasi input kosong (sebelum menyentuh database)
        if (username.isBlank() || password.isBlank()) {
            _loginState.value = LoginState.Error("Username dan password wajib diisi")
            return
        }

        // 2. Cek ke Room Database lewat Repository -> DAO
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            val user = repository.login(username.trim(), password)
            _loginState.value = if (user != null) {
                LoginState.Success(user.username)
            } else {
                LoginState.Error("Username atau Password Salah")
            }
        }
    }

    /** Dipanggil saat user mengetik ulang, agar pesan lama hilang. */
    fun resetState() {
        if (_loginState.value !is LoginState.Loading) {
            _loginState.value = LoginState.Idle
        }
    }

    private fun insertDummyUser() {
        viewModelScope.launch { repository.insertDummyUserIfEmpty() }
    }

    companion object {
        fun factory(repository: UserRepository): ViewModelProvider.Factory = viewModelFactory {
            initializer { LoginViewModel(repository) }
        }
    }
}
