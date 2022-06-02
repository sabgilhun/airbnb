package com.example.todo.airbnb.presentation.search.fare.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.util.toRange
import androidx.navigation.NavController
import com.example.todo.airbnb.domain.model.Search
import com.example.todo.airbnb.presentation.main.components.Destinations
import com.example.todo.airbnb.presentation.search.SearchViewModel
import com.example.todo.airbnb.presentation.search.components.common.BottomScreen
import java.text.DecimalFormat

@ExperimentalMaterialApi
@Composable
fun FareScreen(navController: NavController, viewModel: SearchViewModel) {
    FareContent(navController, viewModel)
}

@ExperimentalMaterialApi
@Composable
fun FareContent(navController: NavController, viewModel: SearchViewModel) {

    var sliderPosition by remember { mutableStateOf(0.01f..1f) }
    val lowerThumb = sliderPosition.toRange().lower
    val upperThumb = sliderPosition.toRange().upper

    Scaffold(
        backgroundColor = Color.White,
        topBar = {
            FareTopAppBar(
                viewModel = viewModel,
                navController = navController,
                lowerThumb = lowerThumb,
                upperThumb = upperThumb
            )
        },
        bottomBar = {
            BottomScreen(
                title = "",
                onRemove = {},
                onSkip = { navController.navigate(Destinations.personnel) }
            )
        }
    ) {
        Fare(viewModel,
            lowerThumb,
            upperThumb,
            sliderPosition = sliderPosition,
            setValue = { sliderPosition = it }
        )
    }
}

@Composable
private fun FareTopAppBar(
    viewModel: SearchViewModel,
    navController: NavController,
    lowerThumb: Float,
    upperThumb: Float,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(Color(0xffF5F5F7)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = { navController.navigateUp() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Icon",
                    tint = Color.Black,
                )
            }

            IconButton(
                onClick = {
                    val reservation = viewModel.search.value
                    val priceReservation = reservation?.copy(
                        minPrice = lowerThumb * 1000000,
                        maxPrice = upperThumb * 1000000
                    ) ?: Search(
                        minPrice = lowerThumb * 1000000,
                        maxPrice = upperThumb * 1000000
                    )
                    viewModel.addReservation(priceReservation)
                    navController.navigate(Destinations.personnel)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Check Icon",
                    tint = Color.Red,
                )
            }
        }
        MakeFareText(lowerThumb, upperThumb)
    }
}

@Composable
private fun MakeFareText(lowerThumb: Float, upperThumb: Float) {
    Column() {
        Text(
            text = "가격 범위",
            fontSize = 10.sp
        )
        val changeMoney = DecimalFormat("#,###")
        if ((upperThumb * 1000000) >= 1000000f) {
            Text(
                text = "${changeMoney.format(lowerThumb * 1000000)}원 - " +
                        "${changeMoney.format(1000000)}원+",
            )
        } else if ((lowerThumb * 1000000 > 1000000f) && (upperThumb * 1000000 > 1000000f)) {
            Text(
                text = "${changeMoney.format(1000000)}원+" +
                        "${changeMoney.format(1000000)}원+",
            )
        } else {
            Text(
                text = "${changeMoney.format(lowerThumb * 1000000)}원 - " +
                        "${changeMoney.format(upperThumb * 1000000)}원",
            )
        }
    }
}