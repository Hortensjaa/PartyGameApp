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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fiksjagame.data.GameState
import com.example.fiksjagame.data.Headers
import com.example.fiksjagame.data.Question
import com.example.fiksjagame.data.Vote
import com.example.fiksjagame.ui.composables.WinnerDialog
import kotlinx.coroutines.delay


@Preview(name = "Phone", device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun QuestionView(
    state: GameState = GameState(
        players = listOf("Adi", "Dawid", "Daniel", "Jula", "Kuba", "Maja",
            "Mati", "Michał", "Natala", "Pati", "Piotrek"),
        question = Question(Headers.WHO, "will be the best parent?")
    ),
    owner: String = "Jula",
    voteAction: (Vote) -> Unit = { _: Vote -> },
    endTurnAction: () -> Unit = {}
) {
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val turnTime = 60
        val winner = state.winner
        var isShown by remember { mutableStateOf(true) }
        var votesLeftStr by remember { mutableStateOf("") }
        var timeLeft by remember { mutableStateOf(turnTime) }

        LaunchedEffect(timeLeft) {
            while (timeLeft > 0) {
                delay(1000L)
                timeLeft--
            }
            endTurnAction()
        }

        LaunchedEffect(state.winner) {
            isShown = state.winner != emptyList<String>()
            timeLeft = turnTime
        }

        LaunchedEffect(state.devicesVotesLeft[owner]) {
            val votesNum : Int = state.devicesVotesLeft[owner] ?: -1
            votesLeftStr = if (votesNum > 1) {
                "$votesNum votes left"
            } else if (votesNum == 1) {
                "1 vote left"
            } else {
                "No votes left"
            }
        }

        Column (
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .padding(20.dp, 30.dp)
                .fillMaxWidth()
        ) {
            Text(text = timeLeft.toString())
            Text(
                votesLeftStr,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth())
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    state.question?.header.toString(),
                    style = MaterialTheme.typography.titleMedium)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    state.question?.text.toString(),
                    style = MaterialTheme.typography.titleLarge)
            }
            LinearProgressIndicator(
                progress = { (timeLeft.toFloat() / 60) },
                modifier = Modifier.fillMaxWidth()
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            ) {
                items(state.players) { voteFor ->
                    Button(
                        onClick = {voteAction(Vote(player = owner, vote=voteFor))},
                        modifier = Modifier.padding(5.dp),
                        enabled = (state.devicesVotesLeft[owner] ?: 0) > 0
                    )
                    {
                        Text(text = voteFor)
                    }
                }
            }
        }
        if (isShown && winner != emptyList<String>()) {
            WinnerDialog(
                winner = winner,
                votesNum = state.votes[winner[0]] ?: 0
            ) { isShown = false }
        }
    }
}
