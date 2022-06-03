package com.example.todo.airbnb.presentation.search.searchresult

import android.util.Log
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.todo.airbnb.presentation.main.components.Destinations
import com.example.todo.airbnb.presentation.search.SearchViewModel

@Composable
fun SearchResultScreen(navController: NavController, viewModel: SearchViewModel) {
    Log.d("TEST", "${viewModel.search.value}")
    Button(onClick = { navController.navigate(Destinations.searchMap) }) {
        Text(text = "SearchResult")
    }
}