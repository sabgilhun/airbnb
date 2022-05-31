package com.example.todo.airbnb.presentation.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.todo.airbnb.common.components.DefaultError
import com.example.todo.airbnb.common.components.DefaultLoading

@Composable
fun LoadMainImage() {
    Box(
        modifier = Modifier
            .height(407.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart,
    ) {
        val painter = rememberImagePainter(
            data = "https://a0.muscache.com/im/pictures/b65bef33-07be-4c55-b613-bb990193e8f6.jpg"
        )
        val painterState = painter.state
        Image(
            painter = painter,
            contentDescription = "Logo Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        when (painterState) {
            is ImagePainter.State.Loading -> DefaultLoading()
            is ImagePainter.State.Error -> DefaultError()
            is ImagePainter.State.Success -> {
                Text(
                    text = "슬기로운\n자연생활",
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 279.dp, top = 32.dp, end = 90.dp)
                        .height(96.dp)
                        .width(254.dp),
                    style = MaterialTheme.typography.h4,
                    lineHeight = 48.sp,
                    color = Color(0xff010101)
                )
                Text(
                    text = "에어비앤비가 엄선한\n위시리스트를 만나보세요",
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 225.dp, top = 136.dp, end = 90.dp)
                        .height(46.dp)
                        .width(254.dp),
                    style = MaterialTheme.typography.body1,
                    lineHeight = 23.sp,
                    color = Color(0xff010101),
                )
                Box(
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 165.dp, top = 206.dp, end = 90.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .height(36.dp)
                        .width(154.dp)
                        .background(Color(0xff333333)),
                ) {
                    Text(
                        text = "여행 아이디어 얻기",
                        style = MaterialTheme.typography.button,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                            .height(20.dp)
                            .width(122.dp),
                        lineHeight = 20.sp,
                        color = Color(0xffffffff),
                    )
                }
            }
        }
    }
}