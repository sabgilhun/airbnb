package com.example.todo.airbnb.presentation.search.fare

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.util.toRange
import androidx.navigation.NavController
import com.example.todo.airbnb.presentation.main.components.Destinations
import com.example.todo.airbnb.presentation.search.SearchViewModel
import java.text.DecimalFormat

@ExperimentalMaterialApi
@Composable
fun FareScreen(navController: NavController, viewModel: SearchViewModel) {
    FareEnroll(navController, viewModel)
}


@ExperimentalMaterialApi
@Composable
fun FareEnroll(navController: NavController, viewModel: SearchViewModel) {

    var sliderPosition by remember { mutableStateOf(0.01f..1f) }

    val lowerThumb = sliderPosition.toRange().lower
    val upperThumb = sliderPosition.toRange().upper



    Scaffold(
        backgroundColor = Color.White,
        topBar = {
            FareTopAppBar(
                navController = navController,
                lowerThumb = lowerThumb,
                upperThumb = upperThumb
            )
        },
        bottomBar = {
            BottomBar()
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.width(350.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Graph(
                    viewModel = viewModel,
                    lowerThumb = lowerThumb,
                    upperThumb = upperThumb,
                    sliderPosition = sliderPosition,
                    setValue = { sliderPosition = it }
                )
                LowestFare(lowerThumb)
                HighestFare(upperThumb)
            }
        }
    }
}

@Composable
private fun BottomBar() {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(0xffF5F5F7)),

        ) {
        Text(
            text = "건너뛰기",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun HighestFare(upperThumb: Float) {
    val changeMoney = DecimalFormat("#,###")
    Column(
        modifier = Modifier
            .padding(top = 13.dp)
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .fillMaxWidth()
            .height(55.dp)
            .background(Color(0xffE0E0E0)),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "최고요금",
            modifier = Modifier.padding(start = 16.dp),
            fontSize = 12.sp
        )
        if ((upperThumb * 1000000) <= 1000000f) {
            Text(
                text = "${changeMoney.format(upperThumb * 1000000)}원",
                modifier = Modifier.padding(start = 16.dp),
                fontSize = 16.sp
            )
        } else {
            Text(
                text = "${changeMoney.format(1000000)}원+",
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
private fun LowestFare(lowerThumb: Float) {
    val changeMoney = DecimalFormat("#,###")
    Column(
        modifier = Modifier
            .padding(top = 59.dp)
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .fillMaxWidth()
            .height(55.dp)
            .background(Color(0xffE0E0E0)),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "최저요금",
            modifier = Modifier.padding(start = 16.dp),
            fontSize = 12.sp
        )
        Text(
            text = "${changeMoney.format(lowerThumb * 1000000)}원",
            modifier = Modifier.padding(start = 16.dp),
            fontSize = 16.sp
        )
    }
}

@Composable
private fun FareTopAppBar(
    navController: NavController,
    lowerThumb: Float,
    upperThumb: Float
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
                onClick = { navController.navigate(Destinations.personnel) }
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

@ExperimentalMaterialApi
@Composable
private fun Graph(
    viewModel: SearchViewModel,
    lowerThumb: Float,
    upperThumb: Float,
    sliderPosition: ClosedFloatingPointRange<Float>,
    setValue: (ClosedFloatingPointRange<Float>) -> Unit
) {
    Column(
        modifier = Modifier
            .height(200.dp)
            .width(350.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier.graphicsLayer {
                rotationX = 180f
            }
        ) {
            for (i in 1..20) {
                val checkQuantity = checkFare(i, viewModel)
                val checkColor = checkColor(i, lowerThumb * 100, upperThumb * 100)
                MakeGraph(i, checkQuantity, checkColor)
            }
        }
    }
    Row(
        modifier = Modifier
            .width(350.dp)
    ) {
        RangeSlider(
            modifier = Modifier
                .background(color = Color(0xffE0E0E0)),
            values = sliderPosition,
            onValueChange = { setValue(it) },
            valueRange = 0f..2f, // 5f == 최대5백만원
            steps = 19,
            colors = SliderDefaults
                .colors(
                    thumbColor = Color.Blue,
                ),
        )
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

@Composable
private fun MakeGraph(index: Int, quantity: Int, color: Color) {
    Box(
        modifier = Modifier
            .padding(start = (index * 16.5).dp) // 350-(20(좌우 Padding10)) / 20(step 값)
            .height((quantity * 30).dp)
            .width(8.dp)
            .background(color)
    )
}

@Composable
private fun checkFare(index: Int, viewModel: SearchViewModel): Int {
    val accommodations = viewModel.accommodations.collectAsState().value
    var count = 0

    accommodations.let {
        for (i in accommodations.indices) {
            // index * Int 에서 Int값에는 step으로 나누어진 돈단위만큼 넣으면됨
            if ((it[i].fare <= index * 100000) && (it[i].fare >= (index - 1) * 100000)) {
                count++
            }
        }
    }
    return count
}

@Composable
private fun checkColor(index: Int, lowerRange: Float, upperRange: Float): Color {
    // index * Int 에서 Int값에는 step으로 나누어진 만원단위만큼 넣으면됨 (ex10만원 = 10)
    if (index * 10 > lowerRange && index * 10 <= upperRange) {
        return Color(0xff828282)
    }
    //바깥색
    return Color(0xffBDBDBD)
}