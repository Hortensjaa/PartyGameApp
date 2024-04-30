package com.example.fiksjagame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.fiksjagame.navigation.Navigation
import com.example.fiksjagame.presentation.GameViewModel
import com.example.fiksjagame.ui.composables.appstates.ConnectionError
import com.example.fiksjagame.ui.composables.appstates.Loading
import com.example.fiksjagame.ui.themes.FiksjaGameTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FiksjaGameTheme {
                val navController = rememberNavController()
                val viewModel = hiltViewModel<GameViewModel>()
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
                    Scaffold {
                        paddingValues ->
                        Box(modifier = Modifier.fillMaxWidth().padding(paddingValues)) {
                            Navigation(
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                    }

                }
            }
        }
    }
}