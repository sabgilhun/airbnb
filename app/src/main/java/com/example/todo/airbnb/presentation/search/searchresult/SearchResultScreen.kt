package com.example.todo.airbnb.presentation.search.searchresult

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.todo.airbnb.R
import com.example.todo.airbnb.common.components.HandleImageResult
import com.example.todo.airbnb.presentation.main.components.Destinations
import com.example.todo.airbnb.presentation.main.components.HomeSections
import com.example.todo.airbnb.presentation.search.SearchViewModel

@Composable
fun SearchResultScreen(navController: NavController, viewModel: SearchViewModel) {

    Scaffold(
        topBar = {
            ResultTopBar(navController, viewModel)
        }
    ) {
        ResultContent(navController, viewModel)
    }
}

@Composable
fun ResultTopBar(navController: NavController, viewModel: SearchViewModel) {
    val search = viewModel.search.value
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { navController.navigateUp() }
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Back Icon",
                tint = Color.Black,
            )
        }

        Text(
            text = "${search?.location ?: ""} ${search?.checkIn ?: ""} ${search?.checkOut ?: ""} ${search?.guest ?: ""}"
        )

        IconButton(
            onClick = {
                navController.navigate(HomeSections.Search.route) {
                    popUpTo(HomeSections.Search.route) { inclusive = true }
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Check Icon",
                tint = Color.Black,
            )
        }
    }
}

@Composable
private fun ResultContent(navController: NavController, viewModel: SearchViewModel) {
    val search = viewModel.search.value
    Log.d("test", "ResultContent: $search")

    Box {
        LazyColumn(
            modifier = Modifier
                .padding(start = 16.5.dp, end = 16.5.dp, top = 12.dp, bottom = 60.dp),
            verticalArrangement = Arrangement.spacedBy(23.dp)
        ) {
            item {
                Text(
                    text = "300개 이상의 숙소",
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(3) {
                AccommodationItem()
            }
        }
        Button(
            onClick = { navController.navigate(Destinations.searchMap) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 90.dp)
                .clip(RoundedCornerShape(10.dp)),
        ) {
            Icon(
                imageVector = Icons.Default.Place,
                contentDescription = "지도"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "지도")
        }
    }
}

@Composable
private fun AccommodationItem() {
    val isClick = remember { mutableStateOf(false) }
    val dummyImage =
        "https://a0.muscache.com/im/pictures/2f13349d-879d-43c6-83e3-8e5679291d53.jpg?im_w=480"

    Column {
        Box() {
            LoadImage(imageURL = dummyImage)
            Box(modifier = Modifier
                .padding(top = 15.dp, start = 8.36.dp)
                .clip(RoundedCornerShape(size = 10.dp))
                .align(Alignment.TopStart)
                .background(Color.White)
            ) {
                Text(
                    text = "슈퍼 호스트",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
            Image(
                painter = if (isClick.value) painterResource(id = R.drawable.ic_favorite_selected) else painterResource(
                    id = R.drawable.ic_favorite),
                contentDescription = "favorite",
                modifier = Modifier
                    .padding(top = 15.dp, end = 8.36.dp)
                    .align(Alignment.TopEnd)
                    .clickable {
                        isClick.value = !isClick.value
                    }
            )
        }

        Spacer(modifier = Modifier.height(8.5.dp))

        Row {
            Image(
                imageVector = Icons.Default.Star,
                contentDescription = "star image",
                colorFilter = ColorFilter.tint(Color.Red)
            )
            Spacer(modifier = Modifier.width(5.5.dp))
            Text(text = "4.80")
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "후기 127개")
        }
        Spacer(modifier = Modifier.height(8.5.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Spacious and Comfortable Cozy",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "W82,953 / 박")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "총액 W1,493,154")
    }
}


@Composable
private fun LoadImage(imageURL: String?) {
    val painter = rememberImagePainter(data = imageURL)
    if (imageURL == null) {
        HandleImageResult(
            painterState = painter.state,
            onEmpty = {
                Image(
                    painter = painterResource(id = R.drawable.ic_error),
                    contentDescription = "Error Image",
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .height(240.dp)
                        .background(Color.LightGray),
                    contentScale = ContentScale.FillBounds
                )
            }
        )
    } else {
        Image(
            painter = painter,
            contentDescription = "숙소 이미지",
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .height(240.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}