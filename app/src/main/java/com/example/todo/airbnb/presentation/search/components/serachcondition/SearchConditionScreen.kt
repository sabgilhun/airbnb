package com.example.todo.airbnb.presentation.search.components.serachcondition

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.todo.airbnb.presentation.main.components.Destinations

@Composable
fun SearchConditionScreen(navController: NavController) {
    Button(onClick = { navController.navigate(Destinations.detail) }) {
        Text(text = "SearchMap")
    }
}