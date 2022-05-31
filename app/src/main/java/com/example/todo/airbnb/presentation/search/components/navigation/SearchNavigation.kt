package com.example.todo.airbnb.presentation.search.components.navigation

import SearchMainScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo.airbnb.presentation.search.SearchViewModel
import com.example.todo.airbnb.presentation.search.components.date.DateScreen

sealed class SearchScreens(var route: String) {
    object SearchList : SearchScreens("검색리스트")
    object Date : SearchScreens("캘린더")
}

@Composable
fun SearchNavGraph(viewModel: SearchViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SearchScreens.SearchList.route
    ) {

        composable(SearchScreens.SearchList.route) {
            SearchMainScreen(
                navController = navController,
                viewModel = viewModel)
        }

        composable(SearchScreens.Date.route) {
            DateScreen(navController = navController)
        }
    }
}