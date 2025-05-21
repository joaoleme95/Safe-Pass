package com.upx.safepass.presentation.passwordList

import androidx.lifecycle.ViewModel
import com.upx.safepass.domain.Password
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PasswordViewModel : ViewModel() {

    private val _passwords = MutableStateFlow<List<Password>>(emptyList())
    val passwords: StateFlow<List<Password>> = _passwords.asStateFlow()

    fun addPassword(title: String, password: String) {
        _passwords.value = _passwords.value + Password(title, password)
    }
}