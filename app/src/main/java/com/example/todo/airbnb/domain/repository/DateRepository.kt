package com.example.todo.airbnb.domain.repository

import com.example.todo.airbnb.data.model.date.CalendarMonth

interface DateRepository {

    fun getDate(): List<CalendarMonth>
}