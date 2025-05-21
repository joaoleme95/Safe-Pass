package com.upx.safepass.presentation.login

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upx.safepass.data.PasswordStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginViewModel(private val passwordStore: PasswordStore) : ViewModel() {

    val password = mutableStateOf("")
    val errorMessage = mutableStateOf("")
    val isFirstAccess = mutableStateOf(false)
    private var savedPassword: String? = null

    init {
        viewModelScope.launch {
            savedPassword = passwordStore.savedPasswordFlow.first()
            isFirstAccess.value = savedPassword == null
        }
    }

    fun checkPassword(onSuccess: () -> Unit) {
        val currentPassword = password.value.trim()
        if (isFirstAccess.value) {
            if (currentPassword.length < 4) {
                errorMessage.value = "Senha deve ter ao menos 4 caracteres"
                return
            }
            viewModelScope.launch {
                passwordStore.savePassword(currentPassword)
                savedPassword = currentPassword
                isFirstAccess.value = false
                errorMessage.value = ""
                password.value = ""
                onSuccess()
            }
        } else {
            if (currentPassword == savedPassword) {
                errorMessage.value = ""
                password.value = ""
                onSuccess()
            } else {
                errorMessage.value = "Senha incorreta."
            }
        }
    }
}