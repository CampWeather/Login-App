package com.example.loginapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginapp.data.local.database.AppDatabase
import com.example.loginapp.data.repository.UserRepository
import com.example.loginapp.ui.screen.LoginScreen
import com.example.loginapp.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Database -> DAO -> Repository (dibuat saat aplikasi dijalankan)
        val database = AppDatabase.getDatabase(applicationContext)
        val repository = UserRepository(database.userDao())

        setContent {
            MaterialTheme {
                Surface {
                    val loginViewModel: LoginViewModel =
                        viewModel(factory = LoginViewModel.factory(repository))
                    LoginScreen(viewModel = loginViewModel)
                }
            }
        }
    }
}
