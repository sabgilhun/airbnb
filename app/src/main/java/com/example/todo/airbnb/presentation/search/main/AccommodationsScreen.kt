package com.example.todo.airbnb.presentation.search.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.todo.airbnb.common.components.HandleImageResult
import com.example.todo.airbnb.presentation.search.SearchViewModel

@Composable
fun SubTitle2() {
    Text(
        text = "어디에서나, 여행은\n살아보는거야!",
        modifier = Modifier
            .padding(top = 32.dp, bottom = 24.dp)
            .height(66.dp)
            .width(343.dp),
        style = MaterialTheme.typography.h5,
        lineHeight = 33.sp,
        color = Color.Black
    )
}

@Composable
fun AccommodationsScreen(viewModel: SearchViewModel) {
    val accommodations = viewModel.accommodations.collectAsState().value
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        SubTitle2()
        LazyRow() {
            items(accommodations) { accommodation ->
                Column(
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    LoadImage(imgUrl = accommodation.imageURL)
                    Text(text = accommodation.description)
                    Spacer(modifier = Modifier.height(60.dp))
                }
            }
        }
    }
}

@Composable
private fun LoadImage(imgUrl: String) {
    val painter = rememberImagePainter(
        data = imgUrl
    )
    Image(
        painter = painter,
        contentDescription = "여행지 이미지",
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .width(242.dp)
            .height(294.dp),
        contentScale = ContentScale.FillBounds
    )
    HandleImageResult(painterState = painter.state)
}