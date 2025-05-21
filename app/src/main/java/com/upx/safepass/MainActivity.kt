package com.upx.safepass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.upx.safepass.data.PasswordStore
import com.upx.safepass.presentation.login.LoginScreen
import com.upx.safepass.presentation.login.LoginViewModel
import com.upx.safepass.presentation.passwordList.PasswordListScreen
import com.upx.safepass.presentation.passwordList.PasswordViewModel

class MainActivity : ComponentActivity() {

    private lateinit var passwordViewModel: PasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val passwordStore = PasswordStore(this)
        val factory = LoginViewModelFactory(passwordStore)
        val loginViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        val passwordViewModel = ViewModelProvider(this)[PasswordViewModel::class.java]

        setContent {
            PasswordManagerApp(
                loginViewModel = loginViewModel,
                passwordViewModel = passwordViewModel
            )
        }
    }
}

class LoginViewModelFactory(private val passwordStore: PasswordStore) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(passwordStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun PasswordManagerApp(
    loginViewModel: LoginViewModel,
    passwordViewModel: PasswordViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController, loginViewModel)
        }
        composable("passwords") {
            PasswordListScreen(passwordViewModel)
        }
    }
}