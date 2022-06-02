package com.example.todo.airbnb.presentation.search.date

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.todo.airbnb.data.datasource.CalendarYear
import com.example.todo.airbnb.domain.model.Search
import com.example.todo.airbnb.presentation.main.components.Destinations
import com.example.todo.airbnb.presentation.search.SearchViewModel
import com.example.todo.airbnb.presentation.search.components.common.BottomScreen
import com.example.todo.airbnb.presentation.search.date.components.Calendar
import com.example.todo.airbnb.presentation.search.date.components.CalendarDay
import com.example.todo.airbnb.presentation.search.date.components.CalendarMonth
import com.example.todo.airbnb.presentation.search.date.components.DaySelected
import com.example.todo.airbnb.ui.theme.Gray

@Composable
fun DateScreen(navController: NavController, viewModel: SearchViewModel) {
    val dateViewModel: DateViewModel = viewModel()
    val calendarYear = dateViewModel.calendarYear

    CalendarContent(
        viewModel = viewModel,
        navController = navController,
        selectedDates = dateViewModel.datesSelected.toString(),
        calendarYear = calendarYear,
        onDayClicked = { calendarDay, calendarMonth ->
            dateViewModel.onDaySelected(
                DaySelected(calendarDay.value.toInt(), calendarMonth, calendarYear)
            )
        },
        onClear = { dateViewModel.onClear() }
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
) {
    Scaffold(
        backgroundColor = Color.White,
        topBar = {
            CalendarTopAppBar(navController, selectedDates, viewModel)
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
                        val split = selectedDates.split(" - ")
                        if (split.size > 1) viewModel.addReservation(
                            Search(split[0], split[1], null)
                        )
                        else viewModel.addReservation(Search(split[0], split[0], null))
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