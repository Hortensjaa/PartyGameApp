package com.example.fiksjagame.ktorClient

import com.example.fiksjagame.data.GameState
import com.example.fiksjagame.data.Vote
import kotlinx.coroutines.flow.Flow

interface RealtimeMessagingClient {
    fun getGameStateStream(): Flow<GameState>
    suspend fun sendVote(vote: Vote)
    suspend fun sendCheck()
    suspend fun sendEndTurn(owner: String)
    suspend fun sendLoggedIn(name: String)
    suspend fun sendAddPlayers(names: List<String>)
    suspend fun sendReady(owner: String)
    suspend fun close()
}