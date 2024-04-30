package com.example.fiksjagame.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(name = "Phone", device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun AddPlayersView(
    addPlayersAction: (List<String>) -> Unit = {},
    navAction: () -> Unit = {}
) {
    var player1: String by remember { mutableStateOf("") }
    var player2: String by remember { mutableStateOf("") }
    var player3: String by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp))
        {
            Text("Add players", style=MaterialTheme.typography.titleLarge)
            Text(
                "Add up to three other players who will play together with you on this phone.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge)
            TextField(
                value = player1,
                onValueChange = { newText: String -> player1 = newText },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                placeholder = { Text("player 1")})
            TextField(
                value = player2,
                onValueChange = { newText: String -> player2 = newText },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                placeholder = { Text("player 2")})
            TextField(
                value = player3,
                onValueChange = { newText: String -> player3 = newText },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                placeholder = { Text("player 3")})
            Button(onClick = {
                addPlayersAction(listOf(player1, player2, player3))
                navAction()
            }) { Text("Enter game") }
        }
    }
}
