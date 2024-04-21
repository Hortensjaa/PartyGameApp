package com.example.fiksjagame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fiksjagame.presentation.FiksjaViewModel
import com.example.fiksjagame.ui.composables.appstates.CheckConnection
import com.example.fiksjagame.ui.composables.appstates.ConnectionError
import com.example.fiksjagame.ui.composables.appstates.Loading
import com.example.fiksjagame.ui.theme.FiksjaGameTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FiksjaGameTheme {
                val viewModel = hiltViewModel<FiksjaViewModel>()
                val state by viewModel.state.collectAsState()
                val isConnecting by viewModel.isConnecting.collectAsState()
                val showConnectionError by viewModel.showConnectionError.collectAsState()

                if(showConnectionError) {
                    ConnectionError()
                    return@FiksjaGameTheme
                }
                if(isConnecting) {
                    Loading()
                }
                else {
                    CheckConnection(viewModel::checkConnection)
                }
            }
        }
    }
}