package com.example.todo.airbnb.data.datasource

import android.annotation.SuppressLint
import com.example.todo.airbnb.presentation.search.date.components.CalendarMonth
import com.example.todo.airbnb.presentation.search.date.components.DatesSelectedState
import com.example.todo.airbnb.presentation.search.date.components.DayOfWeek
import com.example.todo.airbnb.presentation.search.date.components.DaySelected
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

typealias CalendarYear = List<CalendarMonth>

enum class MonthOfYear(val order: Int) {
    January(1),
    February(2),
    March(3),
    April(4),
    May(5),
    June(6),
    July(7),
    August(8),
    September(9),
    October(10),
    November(11),
    December(12);

    companion object {
        fun formatMonth(month: String): Int? {
            return values().find { it.name == month }?.order
        }
    }
}

class DatesLocalDataSource {
    val calendarYear = getDayList()
    val datesSelected = DatesSelectedState(calendarYear)

    fun onDaySelected(daySelected: DaySelected) {
        datesSelected.daySelected(daySelected)
    }

    private fun getDayList(): List<CalendarMonth> = MonthOfYear.values().map {
        val date = LocalDate.of(2021, it.order, 1)
        CalendarMonth(
            name = it.name,
            year = date.year.toString(),
            numDays = date.with(TemporalAdjusters.lastDayOfMonth()).dayOfMonth,
            monthNumber = it.order,
            startDayOfWeek = DayOfWeek.values(date.dayOfWeek.toString()) ?: DayOfWeek.Sunday
        )
    }

    @SuppressLint("VisibleForTests")
    fun onClear() {
        datesSelected.clearDates()
    }
}