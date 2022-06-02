package com.example.todo.airbnb.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.ImagePainter
import com.example.todo.airbnb.R

@Composable
fun HandleImageResult(
    painterState: ImagePainter.State,
    onEmpty: @Composable (() -> Unit) = { DefaultError() },
    onSuccess: @Composable (() -> Unit)? = null,
    onError: @Composable () -> Unit = { DefaultError() },
    onLoading: @Composable () -> Unit = { DefaultLoading() },
) {
    when (painterState) {
        is ImagePainter.State.Empty -> onEmpty()
        is ImagePainter.State.Loading -> onLoading()
        is ImagePainter.State.Success -> onSuccess?.invoke()
        is ImagePainter.State.Error -> onError()
    }
}

@Composable
fun DefaultLoading() {
    CircularProgressIndicator()
}

@Composable
fun DefaultError() {
    Image(
        painter = painterResource(id = R.drawable.ic_error),
        contentDescription = "Error Image",
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .width(70.dp)
            .height(70.dp),
        contentScale = ContentScale.FillBounds
    )
}