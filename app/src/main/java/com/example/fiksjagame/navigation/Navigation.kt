package com.example.fiksjagame.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fiksjagame.presentation.GameViewModel
import com.example.fiksjagame.ui.views.AddPlayersView
import com.example.fiksjagame.ui.views.LoginView
import com.example.fiksjagame.ui.views.MenuView
import com.example.fiksjagame.ui.views.QuestionView
import com.example.fiksjagame.ui.views.WaitingRoomView

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: GameViewModel
){
    val state = viewModel.state.collectAsState()
    NavHost(navController = navController, startDestination = "menu") {
        // main screen
        composable(route = "menu") {
            MenuView(
                navToGame = { navController.navigate("login") }
            )
        }
        // login screen
        composable(route = "login") {
            LoginView(
                loginAction = viewModel::logIn,
                navToWaitingRoom = { navController.navigate("waiting") },
                navToAddPlayers = { navController.navigate("addPlayers") }
            )
        }
        // adding more players to play on one
        composable(route = "addPlayers") {
            AddPlayersView(
                addPlayersAction = viewModel::addPlayers
            ) { navController.navigate("waiting") }
        }
        // waiting room
        composable(route="waiting") {
            WaitingRoomView(
                state = state.value,
                readyAction = viewModel::socketReady
            ) { navController.navigate("question") }
        }
        // game
        composable(route="question") {
            QuestionView(
                state = state.value,
                voteAction = viewModel::vote,
                owner = viewModel.ownerName.value
            )
        }
    }
}