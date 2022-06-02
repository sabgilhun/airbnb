package com.example.todo.airbnb.domain.model

data class Search(
    val location: String = "",
    val checkIn: String? = null,
    val checkOut: String? = null,
    val minPrice: Float? = null,
    val maxPrice: Float? = null,
    val guest: Int? = null,
)
