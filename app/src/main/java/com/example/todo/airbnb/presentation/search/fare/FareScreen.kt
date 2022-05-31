package com.example.todo.airbnb.presentation.search.fare

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.todo.airbnb.presentation.main.components.Destinations

@Composable
fun FareScreen(navController: NavController) {
    Button(onClick = { navController.navigate(Destinations.personnel) }) {
        Text(text = "Fare")
    }
}