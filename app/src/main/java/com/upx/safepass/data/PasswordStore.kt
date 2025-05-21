package com.upx.safepass.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PasswordStore(private val context: Context) {

    private val Context.dataStore by preferencesDataStore(name = "user_prefs")
    private val PASSWORD_KEY = stringPreferencesKey("user_password")

    val savedPasswordFlow: Flow<String?>
        get() = context.dataStore.data.map { prefs ->
            prefs[PASSWORD_KEY]
        }

    suspend fun savePassword(password: String) {
        context.dataStore.edit { prefs ->
            prefs[PASSWORD_KEY] = password
        }
    }
}