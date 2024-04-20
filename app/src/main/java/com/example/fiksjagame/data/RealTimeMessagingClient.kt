package com.example.fiksjagame.data

import kotlinx.coroutines.flow.Flow

interface RealtimeMessagingClient {
    fun getGameStateStream(): Flow<GameState>
    suspend fun sendAction(action: Vote)
    suspend fun sendCheck()
    suspend fun close()
}