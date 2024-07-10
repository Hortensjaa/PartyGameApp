package com.example.fiksjagame.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fiksjagame.data.GameState

@Preview(name = "Phone", device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun WaitingRoomView(
    state: GameState = GameState(
        players = listOf("Adi", "Dawid", "Jula", "Piotrek", "Ania", "Natala")
    ),
    readyAction: () -> Unit = {},
    navAction: () -> Unit = {}
) {
    val playersNum by mutableStateOf(state.players.size)

    LaunchedEffect(state.gameStarted) {
        if (state.gameStarted) {
            navAction()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp))
        {
            Text(
                text = "Waiting room",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$playersNum players connected",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                "Wait until you see all players in waiting room " +
                        "- then click \"Ready\" button below.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                items(state.players) { player ->
                    Text(player,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            // game will start when all players are ready
            Button(onClick = {
                readyAction()
            } ) {
                Text("Ready")
            }
        }
    }
}