package com.example.todo.airbnb.data.model.date

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.todo.airbnb.presentation.search.date.components.DaySelectedStatus

class CalendarDay(val value: String, status: DaySelectedStatus) {
    var status by mutableStateOf(status)
}