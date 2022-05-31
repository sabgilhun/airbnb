package com.example.todo.airbnb.presentation.search.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun bottomScreen(
    title: String,
    onRemove: () -> Unit,
    onSkip: () -> Unit,
) {
    var text = "건너뛰기"
    if (title.isNotEmpty()) {
        text = "지우기"
    }

    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(Color.LightGray)
    ) {
        Spacer(modifier = Modifier.width(20.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Text(text = text,
                Modifier.clickable {
                    if (text == "건너뛰기") {
                        onSkip()
                    } else {
                        onRemove()
                    }
                }
            )
        }
    }
}

