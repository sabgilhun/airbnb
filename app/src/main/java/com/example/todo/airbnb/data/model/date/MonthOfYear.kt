package com.example.todo.airbnb.data.model.date

enum class MonthOfYear(val order: Int) {
    January(1),
    February(2),
    March(3),
    April(4),
    May(5),
    June(6),
    July(7),
    August(8),
    September(9),
    October(10),
    November(11),
    December(12);

    companion object {
        fun formatMonth(month: String): Int? {
            return values().find { it.name == month }?.order
        }
    }
}