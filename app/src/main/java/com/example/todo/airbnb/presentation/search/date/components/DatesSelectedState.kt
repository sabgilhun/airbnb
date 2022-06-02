package com.example.todo.airbnb.presentation.search.date.components

import com.example.todo.airbnb.data.model.date.CalendarYear
import com.example.todo.airbnb.presentation.search.date.components.DaySelected.Companion.DaySelectedEmpty

data class DatesSelectedState(
    val year: CalendarYear,
    val from: DaySelected = DaySelectedEmpty,
    val to: DaySelected = DaySelectedEmpty,
) {
    override fun toString(): String {
        if (from == DaySelectedEmpty && to == DaySelectedEmpty) return ""
        var output = from.toString()
        if (to != DaySelectedEmpty) {
            output += " - $to"
        }
        return output
    }
}