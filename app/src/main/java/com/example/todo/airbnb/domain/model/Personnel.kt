package com.example.todo.airbnb.domain.model

data class Personnel(
    val adult: Int,
    val child: Int,
    val baby: Int
) {
    override fun toString(): String {
        return (adult + child + baby).toString()
    }
}