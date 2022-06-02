package com.example.todo.airbnb.presentation.search.date

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.airbnb.data.datasource.DatesLocalDataSource
import com.example.todo.airbnb.data.repository.DateRepositoryImpl
import com.example.todo.airbnb.domain.repository.DateRepository
import com.example.todo.airbnb.presentation.search.date.components.DaySelected
import kotlinx.coroutines.launch

class DateViewModel(
    private val datesRepository: DateRepository = DateRepositoryImpl(DatesLocalDataSource()),
) : ViewModel() {

    val datesSelected = datesRepository.getDatesSelected()
    val calendarYear = datesRepository.getDate()

    fun onDaySelected(daySelected: DaySelected) = viewModelScope.launch {
        datesRepository.onDaySelected(daySelected)
    }

    fun onClear() = viewModelScope.launch {
        datesRepository.onClear()
    }
}