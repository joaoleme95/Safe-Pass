package com.upx.safepass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.upx.safepass.presentation.login.LoginScreen
import com.upx.safepass.presentation.login.LoginViewModel
import com.upx.safepass.presentation.passwordList.PasswordListScreen
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasswordManagerApp()
        }
    }
}

@Composable
fun PasswordManagerApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            val viewModel: LoginViewModel = viewModel()
            LoginScreen(navController, viewModel)
        }
        composable("passwords") {
            PasswordListScreen()
        }
    }
}