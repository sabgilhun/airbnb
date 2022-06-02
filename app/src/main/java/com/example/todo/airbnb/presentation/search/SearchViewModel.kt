package com.example.todo.airbnb.presentation.search

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.airbnb.data.Accommodations
import com.example.todo.airbnb.data.SearchResult
import com.example.todo.airbnb.data.Spot
import com.example.todo.airbnb.data.Travel
import com.example.todo.airbnb.data.repository.MainRepositoryImpl
import com.example.todo.airbnb.data.repository.SpotRepositoryImpl
import com.example.todo.airbnb.domain.model.Search
import com.example.todo.airbnb.domain.repository.MainRepository
import com.example.todo.airbnb.domain.repository.SpotRepository
import com.example.todo.airbnb.presentation.search.main.SearchWidgetState
import com.example.todo.airbnb.presentation.search.searchmap.MapEvent
import com.example.todo.airbnb.presentation.search.searchmap.MapState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: MainRepository = MainRepositoryImpl(),
    private val spotRepository: SpotRepository = SpotRepositoryImpl(),
) : ViewModel() {

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> = mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    private val _searchLocations = MutableStateFlow<List<Travel>>(emptyList())
    val searchLocations: StateFlow<List<Travel>> = _searchLocations.asStateFlow()

    private val _travelLocations = MutableStateFlow<List<Travel>>(emptyList())
    val travelLocation: StateFlow<List<Travel>> = _travelLocations.asStateFlow()

    private val _accommodations = MutableStateFlow<List<Accommodations>>(emptyList())
    val accommodations: StateFlow<List<Accommodations>> = _accommodations.asStateFlow()

    private var _search: MutableState<Search?> = mutableStateOf(null)
    val search: State<Search?> = _search

    var state by mutableStateOf(MapState())

    init {
        getTravelLocations()
        getSearchLocations("양재")
        getAccommodations()
        getSpots()
    }

    fun addReservation(newSearch: Search) {
        _search.value = newSearch
    }

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchText(newValue: String) {
        _searchTextState.value = newValue
        viewModelScope.launch {
            repository.getSearchWordList(newValue).collect {
                _searchLocations.value = it
            }
        }
    }

    fun getSearchLocations(searchWord: String) {
        viewModelScope.launch {
            repository.getSearchWordList(searchWord).collect {
                _searchLocations.value = it
            }
        }
    }

    private fun getTravelLocations() {
        viewModelScope.launch {
            val travelLocations = repository.getTravelLocations()
            travelLocations.collect {
                _travelLocations.value = it
            }
        }
    }

    private fun getAccommodations() {
        viewModelScope.launch {
            val accommodations = repository.getAccommodations()
            accommodations.collect {
                _accommodations.value = it
            }
        }
    }

    fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.OnMapLongClick -> {
                viewModelScope.launch {
                    Log.d("test", "onEvent: ${event.latLng.latitude} ${event.latLng.longitude}")
                }
            }
            is MapEvent.OnInfoWindowLongClick -> {
                viewModelScope.launch {
                    Log.d("test", "onEvent: ${event.spot}")
                }
            }
        }
    }

    fun getSpots() = viewModelScope.launch {
        spotRepository.getSpots().collectLatest { spots ->
            state = state.copy(searchResult = spots)
        }
    }
}