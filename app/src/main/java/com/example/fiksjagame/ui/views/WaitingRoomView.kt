package com.example.fiksjagame.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fiksjagame.data.GameState

@Preview(name = "Phone", device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun WaitingRoomView(
    state: GameState = GameState(
        players = mapOf(
            "Adi" to false, "Dawid" to false, "Daniel" to false, "Jula" to false,
            "Kuba"  to false, "Maja" to false, "Michał" to false, "Piotrek" to false)
    ),
    startGameAction: () -> Unit = {},
    navAction: () -> Unit = {}
) {
    val playersList = mutableStateOf(state.players.keys.toList())

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp))
        {
            Text(
                text = "Waiting room",
                style = MaterialTheme.typography.titleLarge
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(playersList.value) {player ->
                    Text(player)
                }
            }
            Button(onClick = {
                startGameAction()
                navAction()
            } ) {
                Text("Start game")
            }
        }
    }
}