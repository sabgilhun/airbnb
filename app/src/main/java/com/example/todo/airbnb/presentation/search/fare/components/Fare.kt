package com.example.todo.airbnb.presentation.search.fare.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RangeSlider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo.airbnb.presentation.search.SearchViewModel
import java.text.DecimalFormat

@ExperimentalMaterialApi
@Composable
internal fun Fare(
    viewModel: SearchViewModel,
    lowerThumb: Float,
    upperThumb: Float,
    sliderPosition: ClosedFloatingPointRange<Float>,
    setValue: (ClosedFloatingPointRange<Float>) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .width(350.dp)
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Graph(
                viewModel = viewModel,
                lowerThumb = lowerThumb,
                upperThumb = upperThumb,
                sliderPosition = sliderPosition,
                setValue = { setValue(it) }
            )
            LowestFare(lowerThumb)
            HighestFare(upperThumb)
        }
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

@ExperimentalMaterialApi
@Composable
private fun Graph(
    viewModel: SearchViewModel,
    lowerThumb: Float,
    upperThumb: Float,
    sliderPosition: ClosedFloatingPointRange<Float>,
    setValue: (ClosedFloatingPointRange<Float>) -> Unit,
) {
    Box(modifier = Modifier.width(350.dp)) {
        Box(
            Modifier.graphicsLayer { rotationX = 180f }
        ) {
            for (i in 1..20) {
                val checkQuantity = checkFare(i, viewModel)
                val checkColor = checkColor(i, lowerThumb * 100, upperThumb * 100)
                MakeGraph(i, checkQuantity, checkColor)
            }
        }
        Row(
            modifier = Modifier
                .width(350.dp)
                .padding(top = 98.dp)
                .align(Alignment.BottomCenter)
        ) {
            RangeSlider(
                values = sliderPosition,
                onValueChange = { setValue(it) },
                valueRange = 0f..2f, // 5f == 최대5백만원
                colors = SliderDefaults.colors(
                    thumbColor = Color.LightGray,
                    activeTrackColor = Color.Gray,
                    inactiveTrackColor = Color.LightGray,
                ),
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