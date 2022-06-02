package com.example.todo.airbnb.presentation.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.airbnb.data.Accommodations
import com.example.todo.airbnb.data.Travel
import com.example.todo.airbnb.data.repository.MainRepositoryImpl
import com.example.todo.airbnb.domain.model.Personnel
import com.example.todo.airbnb.domain.model.Search
import com.example.todo.airbnb.domain.repository.MainRepository
import com.example.todo.airbnb.presentation.search.main.SearchWidgetState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: MainRepository = MainRepositoryImpl(),
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

    private val _personnel: MutableState<Personnel> = mutableStateOf(Personnel(0, 0, 0))
    val personnel: State<Personnel> = _personnel

    init {
        getTravelLocations()
        getSearchLocations("양재")
        getAccommodations()
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

    fun updatePersonnelText(newPersonnel: Personnel) {
        _personnel.value = newPersonnel
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
}