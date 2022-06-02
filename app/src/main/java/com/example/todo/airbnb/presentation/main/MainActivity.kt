package com.example.todo.airbnb.presentation.main

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todo.airbnb.presentation.main.components.MainScreen
import com.example.todo.airbnb.presentation.search.SearchViewModel
import com.example.todo.airbnb.ui.theme.AirbnbTheme

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirbnbTheme(viewModel)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun AirbnbTheme(viewModel: SearchViewModel) {
    AirbnbTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            MainScreen(viewModel = viewModel)
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AirbnbTheme {
        MainScreen(SearchViewModel())
    }
}