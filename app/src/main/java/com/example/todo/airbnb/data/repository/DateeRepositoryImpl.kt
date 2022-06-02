package com.example.todo.airbnb.data.repository

import com.example.todo.airbnb.data.datasource.CalendarYear
import com.example.todo.airbnb.data.datasource.DatesLocalDataSource
import com.example.todo.airbnb.domain.repository.DateRepository
import com.example.todo.airbnb.presentation.search.date.components.DatesSelectedState
import com.example.todo.airbnb.presentation.search.date.components.DaySelected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DateRepositoryImpl(
    private val datesLocalDataSource: DatesLocalDataSource,
) : DateRepository {

    override fun getDate(): CalendarYear {
        return datesLocalDataSource.calendarYear
    }

    override fun getDatesSelected(): DatesSelectedState {
        return datesLocalDataSource.datesSelected
    }

    override suspend fun onDaySelected(daySelected: DaySelected) {
        withContext(Dispatchers.IO) {
            datesLocalDataSource.onDaySelected(daySelected)
        }
    }

    override suspend fun onClear() {
        withContext(Dispatchers.IO) {
            datesLocalDataSource.onClear()
        }
    }
}