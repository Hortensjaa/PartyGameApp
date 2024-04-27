package com.example.fiksjagame.ktorClient

import com.example.fiksjagame.data.GameState
import com.example.fiksjagame.data.Vote
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.WebSocketSession
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class KtorRealtimeMessagingClient(
    private val client: HttpClient
): RealtimeMessagingClient {

    private var session: WebSocketSession? = null

    override fun getGameStateStream(): Flow<GameState> {
        return flow {
            session = client.webSocketSession {
                url("ws://192.168.1.35:8080/play")
//                url("ws://192.168.1.31:8080/play")
            }
            val gameStates = session!!
                .incoming
                .consumeAsFlow()
                .filterIsInstance<Frame.Text>()
                .mapNotNull { Json.decodeFromString<GameState>(it.readText()) }

            emitAll(gameStates)
        }
    }

    override suspend fun sendLoggedIn(name: String) {
        session?.outgoing?.send(
            Frame.Text("login#${name}")
        )
    }

    override suspend fun sendReady() {
        session?.outgoing?.send(
            Frame.Text("ready#placeholder")
        )
    }

    override suspend fun sendVote(vote: Vote) {
        session?.outgoing?.send(
            Frame.Text("vote#${Json.encodeToString(vote)}")
        )
    }

    override suspend fun sendCheck() {
        session?.outgoing?.send(
            Frame.Text("check_connection#placeholder}")
        )
    }

    override suspend fun close() {
        session?.close()
        session = null
    }
}