package com.example.todo.airbnb.presentation.search.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.todo.airbnb.common.components.HandleImageResult
import com.example.todo.airbnb.data.Travel
import com.example.todo.airbnb.domain.model.Search
import com.example.todo.airbnb.presentation.main.components.Destinations
import com.example.todo.airbnb.presentation.search.SearchViewModel

@Composable
fun TravelScreen(viewModel: SearchViewModel, navController: NavController) {
    val scrollState = rememberLazyListState()
    val travelLocations = viewModel.travelLocation.collectAsState().value

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 24.dp),
            text = "가까운 여행지 둘러보기",
            style = MaterialTheme.typography.h6
        )

        LazyRow(state = scrollState) {
            itemsIndexed(travelLocations) { index, location ->
                MakeColumn(index, location, travelLocations, viewModel, navController)
            }
        }
    }
}

@Composable
private fun MakeColumn(
    index: Int,
    location: Travel,
    travelLocations: List<Travel>,
    viewModel: SearchViewModel,
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .padding(end = 16.dp)
    ) {
        if (index % 2 == 0) {
            Row(
                modifier = Modifier.clickable {
                    viewModel.addReservation(Search(location = location.name))
                    navController.navigate(Destinations.calendar)
                }
            ) { MakeItem(location) }
            if (index + 1 < travelLocations.size) {
                Row(
                    modifier = Modifier.clickable {
                        viewModel.addReservation(Search(location = location.name))
                        navController.navigate(Destinations.calendar)
                    }
                ) { MakeItem(travelLocations[index + 1]) }
            }
        }
    }
}

@Composable
fun MakeItem(location: Travel) {
    LoadImage(location)
    Spacer(modifier = Modifier.width(16.dp))
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(70.dp)
    ) {
        Text(
            text = location.name,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.width(4.dp))
        if (location.time != null) {
            Text(
                text = location.time,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
private fun LoadImage(travel: Travel) {
    val painter = rememberImagePainter(data = travel.imageURL)
    if (travel.imageURL == null) {
        HandleImageResult(painterState = painter.state)
    } else {
        Image(
            painter = painter,
            contentDescription = "여행지 이미지",
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .width(70.dp)
                .height(70.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}