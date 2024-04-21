package com.example.fiksjagame.ui.views

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fiksjagame.data.Headers
import com.example.fiksjagame.data.Question
import com.example.fiksjagame.ui.theme.FiksjaGameTheme


@Preview(name = "Phone", device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun QuestionView(
    question: Question = Question(Headers.WHO, "będzie najlepszym rodzicem?"),
    playersList: List<String> =
        listOf("Adi", "Dawid", "Daniel", "Jula", "Kuba", "Maja",
            "Mati", "Michał", "Natala", "Pati", "Piotrek")
) {
    FiksjaGameTheme {
        Box (
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
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
                    Text(question.header.toString(), style=MaterialTheme.typography.titleMedium)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(question.text, style = MaterialTheme.typography.titleLarge)
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
                    items(playersList) { player ->
                        Button(onClick = {}, modifier = Modifier.padding(5.dp)) {
                            Text(text = player)
                        }
                    }
                }
            }
        }
    }
}

