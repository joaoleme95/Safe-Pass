package com.upx.safepass.presentation.passwordList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upx.safepass.data.PasswordDatabase
import com.upx.safepass.data.PasswordEntity
import com.upx.safepass.domain.PasswordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordViewModel @Inject constructor(
    private val repository: PasswordRepository
) : ViewModel() {

    private val _passwords = MutableStateFlow<List<PasswordEntity>>(emptyList())
    val passwords = _passwords.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAll().collect { _passwords.value = it }
        }
    }

    fun addPassword(title: String, password: String) {
        viewModelScope.launch {
            repository.insert(PasswordEntity(title = title, password = password))
        }
    }
}