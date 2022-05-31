package com.example.todo.airbnb.presentation.main.components

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.todo.airbnb.presentation.search.SearchViewModel
import com.example.todo.airbnb.presentation.search.SearchWidgetState

@Composable
fun MainScreen(viewModel: SearchViewModel) {

    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) {
        BottomNavGraph(navController, viewModel)
    }
}