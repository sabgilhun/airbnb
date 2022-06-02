package com.example.todo.airbnb.data.model.date

enum class DayOfWeek(var day: String) {
    Sunday("SUNDAY"),
    Monday("MONDAY"),
    Tuesday("TUESDAY"),
    Wednesday("WEDNESDAY"),
    Thursday("THURSDAY"),
    Friday("FRIDAY"),
    Saturday("SATURDAY");

    companion object {
        fun values(day: String): DayOfWeek? {
            return values().find { day == it.day }
        }
    }
}