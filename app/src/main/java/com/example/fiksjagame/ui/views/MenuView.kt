package com.example.fiksjagame.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(name = "Phone", device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun MenuView(
    navToGame: () -> Unit = {},
    navToAddQuestion: () -> Unit = {},
    navToSettings: () -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp))
        {
            Text(
                text = "Fiksja",
                fontFamily = FontFamily.Monospace,
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF8E4957),
                            Color(0xFFBA1A1A),
                            Color(0xFF7A0206),
                        ))
                )
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Button(onClick = { navToGame() }, Modifier.width(200.dp)) {
                Text("Enter game")
            }
            OutlinedButton(onClick = { navToAddQuestion() }, Modifier.width(200.dp)) {
                Text("Add question")
            }
            OutlinedButton(onClick = { navToSettings() }, Modifier.width(200.dp)) {
                Text("Settings")
            }
        }
    }
}