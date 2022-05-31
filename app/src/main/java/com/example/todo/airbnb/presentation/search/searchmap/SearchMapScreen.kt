package com.example.todo.airbnb.presentation.search.searchmap

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.todo.airbnb.presentation.main.components.Destinations

@Composable
fun SearchMapScreen(navController: NavController) {
    Button(onClick = { navController.navigate(Destinations.searchCondition) }) {
        Text(text = "SearchMap")
    }
}