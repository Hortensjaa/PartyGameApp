package com.example.fiksjagame.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Preview(name = "Phone", device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun WinnerDialog(
    winner: String = "Winner",
    votesNum: Int = 5,
    hideAction: () -> Unit = {}
){
    Dialog(onDismissRequest = { hideAction() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(20.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 40.dp, horizontal = 20.dp)
            ) {
                Text(
                    text = "The winner is...",
                    style = MaterialTheme.typography.titleMedium)
                Text(
                    text = winner,
                    style = MaterialTheme.typography.titleLarge)
                Text(
                    text = "with $votesNum votes",
                    style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}
