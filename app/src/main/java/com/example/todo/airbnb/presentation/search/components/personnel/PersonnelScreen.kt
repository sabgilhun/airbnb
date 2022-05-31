package com.example.todo.airbnb.presentation.search.components.personnel

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.todo.airbnb.presentation.main.components.Destinations

@Composable
fun PersonnelScreen(navController: NavController) {

    Button(onClick = { navController.navigate(Destinations.searchResult) }) {
        Text(text = "Personnel")
    }
}