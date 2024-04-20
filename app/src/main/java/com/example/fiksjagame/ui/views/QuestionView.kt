package com.example.fiksjagame.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fiksjagame.data.Headers
import com.example.fiksjagame.data.Question


@Preview(name = "Phone", device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun QuestionView(
    question: Question = Question(Headers.WHO, "będzie najlepszym rodzicem?"),
    playersList: List<String> =
        listOf("Adi", "Dawid", "Daniel", "Jula", "Kuba", "Maja",
            "Mati", "Michał", "Natala", "Pati", "Piotrek")
) {
    Column (
        modifier = Modifier.padding(10.dp, 10.dp)
    ) {
        fun modifier(color: Color, height: Float): Modifier {
            return Modifier
                .padding(10.dp, 10.dp)
                .background(color = color)
                .fillMaxWidth()
                .padding(20.dp, 20.dp)
                .fillMaxHeight(height)
        }

        Row (
            modifier = modifier(Color.Blue, .1f)
        ){
            Text(question.header.toString())
        }
        Row (
            modifier = modifier(Color.Red, .2f)
        ){
            Text(question.text)
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier(Color.Green, 1f)
        ) {
            items(playersList) { player ->
                Button(onClick = {}, modifier = Modifier.padding(5.dp)) {
                    Text(text = player)
                }
            }
        }
    }
}

