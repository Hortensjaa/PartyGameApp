package com.example.fiksjagame.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fiksjagame.presentation.GameViewModel
import com.example.fiksjagame.ui.views.LoginView
import com.example.fiksjagame.ui.views.QuestionView

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: GameViewModel
){
    val state = viewModel.state.collectAsState()
    NavHost(navController = navController, startDestination = "login") {
        composable(route = "login") {
            LoginView(viewModel::logIn) { navController.navigate("question") }
        }
        composable(route="question") {
            QuestionView(state.value)
        }
    }
}