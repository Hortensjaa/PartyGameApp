package com.example.fiksjagame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.fiksjagame.navigation.Navigation
import com.example.fiksjagame.presentation.GameViewModel
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
                    Navigation(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}