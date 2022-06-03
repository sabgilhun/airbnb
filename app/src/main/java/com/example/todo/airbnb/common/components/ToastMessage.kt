package com.example.todo.airbnb.common.components

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable

@Composable
fun ToastMessage(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT)
        .show()
}