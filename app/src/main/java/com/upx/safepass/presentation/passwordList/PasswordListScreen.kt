package com.upx.safepass.presentation.passwordList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.upx.safepass.presentation.addPassword.AddPasswordDialog

@Composable
fun PasswordListScreen(viewModel: PasswordViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    val passwords by viewModel.passwords.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar senha")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            if (passwords.isEmpty()) {
                Text("Nenhuma senha salva.", style = MaterialTheme.typography.bodyLarge)
            } else {
                LazyColumn {
                    items(passwords) { password ->
                        Text("${password.title}: ${password.password}", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }

    if (showDialog) {
        AddPasswordDialog(
            onDismiss = { showDialog = false },
            onSave = { title, pass ->
                viewModel.addPassword(title, pass)
                showDialog = false
            }
        )
    }
}