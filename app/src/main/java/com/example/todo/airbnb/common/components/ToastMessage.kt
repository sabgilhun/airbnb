package com.example.todo.airbnb.common.components

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable

@Composable
fun ToastMessage(context: Context, text: String, onShow: (() -> Unit)? = null) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT)
        .show()
    onShow?.invoke()
}