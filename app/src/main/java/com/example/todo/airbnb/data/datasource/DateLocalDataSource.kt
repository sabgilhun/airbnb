package com.example.todo.airbnb.data.datasource

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.todo.airbnb.presentation.search.date.components.CalendarMonth
import com.example.todo.airbnb.presentation.search.date.components.DatesSelectedState
import com.example.todo.airbnb.presentation.search.date.components.DayOfWeek
import com.example.todo.airbnb.presentation.search.date.components.DaySelected
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

typealias CalendarYear = List<CalendarMonth>

@RequiresApi(Build.VERSION_CODES.O)
class DatesLocalDataSource {

    private val dayOfWeek = listOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )

    val calendarYear = getDayList()
    val datesSelected = DatesSelectedState(calendarYear)

    fun onDaySelected(daySelected: DaySelected) {
        datesSelected.daySelected(daySelected)
    }

    private fun getDayList(): List<CalendarMonth> {
        val list = mutableListOf<CalendarMonth>()
        for (month in 1..12) {
            val date = LocalDate.of(2021, month, 1)
            list.add(
                CalendarMonth(
                    name = dayOfWeek[month - 1],
                    year = date.year.toString(),
                    numDays = date.with(TemporalAdjusters.lastDayOfMonth()).dayOfMonth,
                    monthNumber = month,
                    startDayOfWeek = DayOfWeek.values(date.dayOfWeek.toString()) ?: DayOfWeek.Sunday
                )
            )
        }
        return list
    }

    @SuppressLint("VisibleForTests")
    fun onClear() {
        datesSelected.clearDates()
    }
}