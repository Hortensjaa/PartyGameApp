package com.example.fiksjagame.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiksjagame.data.GameState
import com.example.fiksjagame.data.Vote
import com.example.fiksjagame.ktorClient.RealtimeMessagingClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class GameViewModel@Inject constructor(
    private val client: RealtimeMessagingClient
): ViewModel() {
    val state = client
        .getGameStateStream()
        .onStart { _isConnecting.value = true }
        .onEach { _isConnecting.value = false }
        .catch { t -> _showConnectionError.value = t is ConnectException }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), GameState())

    private val _isConnecting = MutableStateFlow(false)
    val isConnecting = _isConnecting.asStateFlow()

    private val _showConnectionError = MutableStateFlow(false)
    val showConnectionError = _showConnectionError.asStateFlow()

    private val _playerName = MutableStateFlow("")
    val playerName = _playerName.asStateFlow()

    fun logIn(name: String) {
        viewModelScope.launch {
            _playerName.value = name
            client.sendLoggedIn(name)
        }
    }

    fun vote(vote: Vote) {
        viewModelScope.launch {
            client.sendVote(vote)
            Log.d("vote","player ${_playerName.value} voted for ${vote.vote}")
        }
    }

    fun checkConnection() {
        viewModelScope.launch {
            client.sendCheck()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            client.close()
        }
    }
}