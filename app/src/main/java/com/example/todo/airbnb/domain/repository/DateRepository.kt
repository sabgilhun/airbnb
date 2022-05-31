package com.example.todo.airbnb.domain.repository

import com.example.todo.airbnb.data.datasource.CalendarYear
import com.example.todo.airbnb.presentation.search.date.components.DatesSelectedState
import com.example.todo.airbnb.presentation.search.date.components.DaySelected

interface DateRepository {
    fun getDate(): CalendarYear

    fun getDatesSelected(): DatesSelectedState

    suspend fun onDaySelected(daySelected: DaySelected)

    suspend fun onClear()
}