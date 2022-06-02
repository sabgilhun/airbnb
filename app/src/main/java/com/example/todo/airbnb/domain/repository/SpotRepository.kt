package com.example.todo.airbnb.domain.repository

import com.example.todo.airbnb.data.SearchResult
import kotlinx.coroutines.flow.Flow

interface SpotRepository {

    fun getSpots(): Flow<List<SearchResult>>
}