package com.example.fiksjagame.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(name = "Phone", device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun LoginView(
    loginAction: (String) -> Unit = {_: String -> }
) {
    var name: String by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp))
        {
            Text(
                text = "Enter your name",
                style = MaterialTheme.typography.titleMedium
            )
            OutlinedTextField(
                value = name,
                onValueChange = { newText: String -> name = newText }
            )
            Button(onClick = {loginAction(name)} ) {
                Text("Enter game")
            }
        }

    }
}