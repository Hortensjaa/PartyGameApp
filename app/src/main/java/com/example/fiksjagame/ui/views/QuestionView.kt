package com.example.fiksjagame.ui.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fiksjagame.data.GameState
import com.example.fiksjagame.data.Headers
import com.example.fiksjagame.data.Question


@Preview(name = "Phone", device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun QuestionView(
    state: GameState = GameState(
        players = mapOf(
            "Adi" to false, "Dawid" to false, "Daniel" to false, "Jula" to false,
            "Kuba"  to false, "Maja" to false, "Mati" to false, "Michał" to false,
            "Natala" to false, "Pati" to false, "Piotrek" to false),
        question = Question(Headers.WHO, "będzie najlepszym rodzicem?")
    )
) {
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val question by remember { mutableStateOf(state.question) }
        val playersList = mutableStateOf(state.players.keys.toList())

//        fixme: why i cant log 2 players? XD
        Log.d("sddsmdskm1", state.players.toString())
        Log.d("sddsmdskm2", playersList.toString())
        Column (
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .padding(20.dp, 30.dp)
                .fillMaxWidth()
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(state.question?.header.toString(), style=MaterialTheme.typography.titleMedium)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                question?.text?.let { Text(it, style = MaterialTheme.typography.titleLarge) }
            }
            LinearProgressIndicator(
                progress = { 0.2f },
                modifier = Modifier.fillMaxWidth()
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            ) {
                items(playersList.value) { player ->
                    Button(onClick = {}, modifier = Modifier.padding(5.dp)) {
                        Text(text = player)
                    }
                }
            }
        }
    }
}
