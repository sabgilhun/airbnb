package com.example.todo.airbnb.presentation.search.personnel.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todo.airbnb.R
import com.example.todo.airbnb.common.components.ToastMessage
import com.example.todo.airbnb.domain.model.Personnel
import com.example.todo.airbnb.domain.model.Search
import com.example.todo.airbnb.presentation.main.components.Destinations
import com.example.todo.airbnb.presentation.search.SearchViewModel
import com.example.todo.airbnb.presentation.search.components.common.BottomScreen

@Composable
fun PersonnelScreen(
    navController: NavController,
    viewModel: SearchViewModel,
) {
    var adultPersonnelText by rememberSaveable { mutableStateOf(0) }
    var childPersonnelText by rememberSaveable { mutableStateOf(0) }
    var babyPersonnelText by rememberSaveable { mutableStateOf(0) }

    val personnel = Personnel(adultPersonnelText, childPersonnelText, babyPersonnelText)
    viewModel.updatePersonnelText(personnel)

    if (adultPersonnelText == 0 && ((childPersonnelText >= 1) || (babyPersonnelText >= 1))) {
        ToastMessage(LocalContext.current, " 유아 및 어린이는 성인과 함께 동반하셔야 됩니다.")
        adultPersonnelText++
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                PersonnelTopAppBar(navController, viewModel.personnel.value, viewModel)
                TotalPersonnelText(viewModel.personnel.value)
            }
        },
        bottomBar = {
            BottomScreen(
                title = bottomBarText(personnel),
                onRemove = {
                    adultPersonnelText = 0
                    childPersonnelText = 0
                    babyPersonnelText = 0
                },
                onSkip = { navController.navigate(Destinations.searchResult) }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            PersonnelItem(
                PersonnelText = adultPersonnelText,
                countPlus = { adultPersonnelText++ },
                countMinus = { adultPersonnelText-- },
                lifeCycle = "성인",
                ageLimit = "만 13세 이상"
            )
            CustomSpacer()
            PersonnelItem(
                PersonnelText = childPersonnelText,
                countPlus = { childPersonnelText++ },
                countMinus = { childPersonnelText-- },
                lifeCycle = "어린이",
                ageLimit = "만 2~12세"
            )
            CustomSpacer()
            PersonnelItem(
                PersonnelText = babyPersonnelText,
                countPlus = { babyPersonnelText++ },
                countMinus = { babyPersonnelText-- },
                lifeCycle = "유아",
                ageLimit = "만 2세 미만"
            )
        }
    }
}

@Composable
private fun PersonnelTopAppBar(
    navController: NavController,
    personnel: Personnel,
    viewModel: SearchViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(Color(0xffF5F5F7)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = { navController.navigateUp() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Icon",
                    tint = Color.Black,
                )
            }
            IconButton(

                onClick = {
                    val reservation = viewModel.search.value
                    val priceReservation = reservation?.copy(
                        guest = personnel
                    ) ?: Search()
                    viewModel.addReservation(priceReservation)
                    navController.navigate(Destinations.searchResult)
                },
                enabled = checkButtonEnabled(personnel)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Check Icon",
                    tint = checkButtonColor(personnel)
                )
            }
        }
    }
}

@Composable
private fun checkButtonEnabled(personnel: Personnel): Boolean {
    if (personnel.adult == 0 && personnel.child == 0 && personnel.baby == 0)
        return false
    return true
}

@Composable
private fun checkButtonColor(personnel: Personnel): Color {
    if (personnel.adult == 0 && personnel.child == 0 && personnel.baby == 0)
        return Color(0xffBDBDBD)
    return Color(0xffE84C60)
}

@Composable
private fun TotalPersonnelText(
    personnel: Personnel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(Color(0xffF5F5F7)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.width(207.dp)
        ) {
            Text(
                text = "인원 입력",
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "게스트 ${personnel.adult + personnel.child}, 유아${personnel.baby}",
                fontSize = 19.sp
            )
        }
    }
}

@Composable
private fun PersonnelItem(
    PersonnelText: Int,
    countPlus: () -> Unit,
    countMinus: () -> Unit,
    lifeCycle: String,
    ageLimit: String
) {
    Row(
        modifier = Modifier
            .height(75.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = lifeCycle,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 12.dp)
            )
            Text(
                text = ageLimit,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
            )
        }
        Row(
            modifier = Modifier.height(36.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = countMinus,
                enabled = PersonnelText > 0
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_outline_remove_circle),
                    contentDescription = "Remove Icon",
                    tint = minusButtonColors(PersonnelText),
                    modifier = Modifier.size(30.dp),
                )
            }
            Text(
                text = "$PersonnelText"
            )
            IconButton(
                onClick = countPlus,
                enabled = PersonnelText < 8
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_outline_add_circle),
                    contentDescription = "Add Icon",
                    tint = plusButtonColors(PersonnelText),
                    modifier = Modifier.size(30.dp),
                )
            }
        }
    }
}

@Composable
private fun minusButtonColors(PersonnelText: Int): Color {
    if (PersonnelText == 0) return Color(0xffBDBDBD)
    return Color(0xff333333)
}

@Composable
private fun plusButtonColors(PersonnelText: Int): Color {
    if (PersonnelText == 8) return Color(0xffBDBDBD)
    return Color(0xff333333)
}

@Composable
private fun CustomSpacer() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .border(width = 1.dp, color = Color(0xffE0E0E0))
    )
}

@Composable
private fun bottomBarText(personnel: Personnel): String {
    if (personnel.adult + personnel.child + personnel.baby == 0) return ""
    return "0이아님"
}