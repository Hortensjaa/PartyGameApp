package com.example.fiksjagame.ui.composables.appstates

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CheckConnection(
    checkAction: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(
                text = "Check connection"
            )
            Button(onClick = checkAction ) {
                Text("Click me to check connection")
            }
        }

    }
}
