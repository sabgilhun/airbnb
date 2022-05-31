package com.example.todo.airbnb.presentation.search.date.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class DayOfWeek(var day: String) {
    Sunday("SUNDAY"),
    Monday("MONDAY"),
    Tuesday("TUESDAY"),
    Wednesday("WEDNESDAY"),
    Thursday("THURSDAY"),
    Friday("FRIDAY"),
    Saturday("SATURDAY");

    companion object {
        fun values(day: String): DayOfWeek? {
            return values().find { day == it.day }
        }
    }
}

enum class DaySelectedStatus {
    NoSelected, Selected, NonClickable, FirstDay, LastDay, FirstLastDay
}

class CalendarDay(val value: String, status: DaySelectedStatus) {
    var status by mutableStateOf(status)
}