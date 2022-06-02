package com.example.todo.airbnb.presentation.search.searchmap

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.todo.airbnb.presentation.main.components.Destinations
import com.example.todo.airbnb.presentation.search.SearchViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker

@Composable
fun SearchMapScreen(navController: NavController, viewModel: SearchViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        SearchMapContent(navController, viewModel)
    }
}

@Composable
private fun SearchMapContent(
    navController: NavController,
    viewModel: SearchViewModel,
) {
    Column {
        Button(onClick = { navController.navigate(Destinations.searchCondition) }) {
            Text(text = "SearchMap")
        }

        val scaffoldState = rememberScaffoldState()
        val uiSettings = remember {
            MapUiSettings(zoomControlsEnabled = true)
        }
        Scaffold(
            scaffoldState = scaffoldState,

            ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                properties = viewModel.state.properties,
                uiSettings = uiSettings,
                onMapLongClick = {
                    viewModel.onEvent(MapEvent.OnMapLongClick(it))
                },
            ) {
//                viewModel.state.spots.forEach { spot ->
//                    Marker(
//                        position = LatLng(spot.latitude, spot.longitude),
//                        title = "Spot (${spot.latitude} ${spot.longitude})",
//                        snippet = "Long click to delete",
//                        onInfoWindowLongClick = {
//                            viewModel.onEvent(MapEvent.OnInfoWindowLongClick(spot))
//                        },
//                        onClick = { true },
//                        icon = BitmapDescriptorFactory.defaultMarker()
//                    )
//
//                }
            }
        }
    }
}
