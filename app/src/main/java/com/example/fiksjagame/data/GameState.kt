package com.example.fiksjagame.data

import kotlinx.serialization.Serializable

@Serializable
data class GameState (
    val players: List<String> = emptyList(),
    val devicesVotesMemo: Map<String, Int> = emptyMap(),
    val devicesVotesLeft: Map<String, Int> = emptyMap(),
    val question: Question? = null,
    val votes: Map<String, Int> = emptyMap(),
    val winner: List<String> = emptyList(),
    val message: String? = null,
    val gameStarted: Boolean = false
)