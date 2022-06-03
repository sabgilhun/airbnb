package com.example.todo.airbnb.presentation.search.date.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todo.airbnb.data.model.date.CalendarDay
import com.example.todo.airbnb.data.model.date.CalendarMonth
import com.example.todo.airbnb.data.model.date.CalendarYear
import com.example.todo.airbnb.domain.model.Search
import com.example.todo.airbnb.presentation.main.components.Destinations
import com.example.todo.airbnb.presentation.search.SearchViewModel
import com.example.todo.airbnb.presentation.search.components.common.BottomScreen
import com.example.todo.airbnb.presentation.search.date.DateViewModel
import com.example.todo.airbnb.ui.theme.Gray

@Composable
fun DateScreen(navController: NavController, viewModel: SearchViewModel) {
    val dateViewModel: DateViewModel = viewModel()
    val dateState = dateViewModel.dates.value

    CalendarContent(
        viewModel = viewModel,
        navController = navController,
        selectedDates = dateViewModel.dates.value.toString(),
        calendarYear = dateState.year,
        onDayClicked = { calendarDay, calendarMonth ->
            dateViewModel.onDaySelected(
                DaySelected(calendarDay.value.toInt(), calendarMonth, dateState.year)
            )
        },
        onClear = { dateViewModel.onClear() },
        dateViewModel = dateViewModel
    )
}

@Composable
fun CalendarContent(
    viewModel: SearchViewModel,
    navController: NavController,
    selectedDates: String,
    calendarYear: CalendarYear,
    onDayClicked: (CalendarDay, CalendarMonth) -> Unit,
    onClear: () -> Unit,
    dateViewModel: DateViewModel,
) {
    Scaffold(
        backgroundColor = Color.White,
        topBar = {
            CalendarTopAppBar(navController, selectedDates, viewModel, dateViewModel)
        },
        bottomBar = {
            BottomScreen(
                selectedDates,
                onRemove = { onClear() },
                onSkip = { navController.navigate(Destinations.fare) }
            )
        }
    ) {
        Calendar(calendarYear, onDayClicked)
    }
}

@Composable
fun CalendarTopAppBar(
    navController: NavController,
    selectedDates: String,
    viewModel: SearchViewModel,
    dateViewModel: DateViewModel,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(153.dp),
        elevation = 0.dp,
        color = Gray
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val color = if (selectedDates.isEmpty()) {
                    Color.LightGray
                } else Color.Red
                IconButton(
                    onClick = { navController.navigateUp() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Icon",
                        tint = Color.Black,
                    )
                }
                IconButton(onClick = {
                    if (selectedDates.isNotEmpty()) {
                        val reservation = viewModel.search.value
                        val split = selectedDates.split(" - ")
                        val from = dateViewModel.dates.value.from
                        val to = dateViewModel.dates.value.to

                        val fromMonth =
                            if (from.month.monthNumber in (1..9)) "0${from.month.monthNumber}" else "${from.month.monthNumber}"
                        val fromDay = if (from.day in (1..9)) "0${from.day}" else "${from.day}"
                        val toMonth =
                            if (to.month.monthNumber in (1..9)) "0${to.month.monthNumber}" else "${to.month.monthNumber}"
                        val toDay = if (to.day in (1..9)) "0${to.day}" else "${to.day}"

                        val checkIn = "${from.month.year}-${fromMonth}-${fromDay}"
                        val checkOut = "${to.month.year}-${toMonth}-${toDay}"

                        if (split.size > 1) viewModel.addReservation(
                            reservation?.copy(checkIn = checkIn, checkOut = checkOut) ?: Search()
                        )
                        else viewModel.addReservation(
                            reservation?.copy(checkIn = checkIn, checkOut = checkIn) ?: Search()
                        )
                        navController.navigate(Destinations.fare)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Check Icon",
                        tint = color
                    )
                }
            }
            Text(
                text = "여행 일정",
                modifier = Modifier.padding(start = 76.dp)
            )
            if (selectedDates.isEmpty()) {
                Text(
                    text = "날짜를 골라주세요.",
                    modifier = Modifier.padding(start = 76.dp),
                    style = MaterialTheme.typography.h5
                )
            } else {
                Text(
                    text = selectedDates,
                    modifier = Modifier.padding(start = 76.dp),
                    style = MaterialTheme.typography.h5
                )
            }
        }
    }
}