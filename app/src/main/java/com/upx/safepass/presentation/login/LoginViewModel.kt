package com.upx.safepass.presentation.login

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    val password = mutableStateOf("")
    val errorMessage = mutableStateOf("")

    private val sharedPreferences = EncryptedSharedPreferences.create(
        "secure_prefs",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        application.applicationContext,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun isPasswordCorrect(): Boolean {
        val saved = sharedPreferences.getString("main_password", null)
        return if (saved == null) {
            sharedPreferences.edit().putString("main_password", password.value).apply()
            true
        } else {
            password.value == saved
        }
    }
}