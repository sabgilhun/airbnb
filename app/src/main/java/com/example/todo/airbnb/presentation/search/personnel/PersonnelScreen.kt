package com.example.todo.airbnb.presentation.search.personnel

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.todo.airbnb.presentation.main.components.Destinations
import com.example.todo.airbnb.presentation.search.SearchViewModel

@Composable
fun PersonnelScreen(
    navController: NavController,
    viewModel: SearchViewModel,
) {

    Column {
        Log.d("test", "PersonnelScreen: ${viewModel.search.value}")
        Button(onClick = { navController.navigate(Destinations.searchResult) }) {
            Text(text = "Personnel")
        }
    }

}