package com.example.todo.airbnb.domain.model

import java.time.LocalDate

data class Search(
    val location: String = "",
    val checkIn: LocalDate? = null,
    val checkOut: LocalDate? = null,
    val minPrice: Float? = null,
    val maxPrice: Float? = null,
    val guest: Int? = null,
)
