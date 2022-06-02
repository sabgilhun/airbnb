package com.example.todo.airbnb.data.repository

import com.example.todo.airbnb.data.SearchResult
import com.example.todo.airbnb.data.Spot
import com.example.todo.airbnb.domain.repository.SpotRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SpotRepositoryImpl(): SpotRepository {
    override fun getSpots(): Flow<List<SearchResult>> = flow {
        val map = (0..10).map {
            SearchResult(
                name = "빌딩 $it",
                fullAddress = "주소 $it",
                spot = Spot(
                    it.toDouble(),
                    it.toDouble()
                )
            )
        }
        emit(map)
    }
}