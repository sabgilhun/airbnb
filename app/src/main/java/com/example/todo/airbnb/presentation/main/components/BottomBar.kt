package com.example.todo.airbnb.presentation.main.components

import SearchScreen
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.todo.airbnb.R
import com.example.todo.airbnb.presentation.reservation.components.ReservationScreen
import com.example.todo.airbnb.presentation.search.SearchViewModel
import com.example.todo.airbnb.presentation.wishlist.components.WishListScreen
import com.example.todo.airbnb.ui.theme.Gray

sealed class NavigationItem(
    var route: String,
    var icon: Int,
    var title: String,
) {
    object Search : NavigationItem("검색", R.drawable.ic_search, "검색")
    object WishList : NavigationItem("위시리스트", R.drawable.ic_favorite, "위시리스트")
    object Reservation : NavigationItem("내 예약", R.drawable.ic_reservation, "내 예약")
}

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(NavigationItem.Search, NavigationItem.WishList, NavigationItem.Reservation)

    BottomNavigation(backgroundColor = Gray) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(painter = painterResource(id = item.icon), contentDescription = item.title)
                },
                label = { Text(text = item.title) },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.Black,
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun BottomNavGraph(navController: NavHostController, viewModel: SearchViewModel) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Search.route
    ) {
        composable(NavigationItem.Search.route) { SearchScreen(viewModel) }
        composable(NavigationItem.WishList.route) { WishListScreen() }
        composable(NavigationItem.Reservation.route) { ReservationScreen() }
    }
}