package com.example.todo.airbnb.presentation.search.main

sealed class SearchWidgetState {
    object OPEN : SearchWidgetState()
    object CLOSED : SearchWidgetState()
}