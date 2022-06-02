package com.example.todo.airbnb.presentation.search.searchmap

import com.example.todo.airbnb.data.SearchResult
import com.google.maps.android.compose.MapProperties

data class MapState(
    val properties: MapProperties = MapProperties(),
    val searchResult: List<SearchResult> = emptyList(),
    val isFalloutMap: Boolean = false,
)