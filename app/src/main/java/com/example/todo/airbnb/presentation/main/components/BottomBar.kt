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
import androidx.lifecycle.Lifecycle
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import com.example.todo.airbnb.R
import com.example.todo.airbnb.data.Travel
import com.example.todo.airbnb.presentation.reservation.components.ReservationScreen
import com.example.todo.airbnb.presentation.search.SearchViewModel
import com.example.todo.airbnb.presentation.search.components.date.DateScreen
import com.example.todo.airbnb.presentation.search.components.detail.DetailScreen
import com.example.todo.airbnb.presentation.search.components.fare.FareScreen
import com.example.todo.airbnb.presentation.search.components.personnel.PersonnelScreen
import com.example.todo.airbnb.presentation.search.components.searchmap.SearchMapScreen
import com.example.todo.airbnb.presentation.search.components.searchresult.SearchResultScreen
import com.example.todo.airbnb.presentation.search.components.serachcondition.SearchConditionScreen
import com.example.todo.airbnb.presentation.wishlist.components.WishListScreen
import com.example.todo.airbnb.ui.theme.Gray

object Destinations {
    const val search = "search"
    const val calendar = "calendar"
    const val fare = "fare"
    const val personnel = "personnel"
    const val searchResult = "result"
    const val searchMap = "map"
    const val searchCondition = "condition"
    const val detail = "detail"
}

sealed class HomeSections(
    var route: String,
    var icon: Int,
    var title: String,
) {
    object Search : HomeSections("검색", R.drawable.ic_search, "검색")
    object WishList : HomeSections("위시리스트", R.drawable.ic_favorite, "위시리스트")
    object Reservation : HomeSections("내 예약", R.drawable.ic_reservation, "내 예약")
}

@Composable
fun BottomBar(
    navController: NavController,
    bottomBarTabs: List<HomeSections>,
) {
    BottomNavigation(backgroundColor = Gray) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomBarTabs.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(painter = painterResource(id = item.icon), contentDescription = item.title)
                },
                label = { Text(text = item.title) },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.Black,
                alwaysShowLabel = true,
                selected = (currentRoute == item.route) || selectNavigation(currentRoute, item),
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
        startDestination = Destinations.search
    ) {
        airbnbNavGraph(
            navController = navController,
            viewModel = viewModel
        )
    }
}

private fun NavGraphBuilder.airbnbNavGraph(
    navController: NavController,
    viewModel: SearchViewModel,
) {
    navigation(
        route = Destinations.search,
        startDestination = HomeSections.Search.route
    ) {
        composable(HomeSections.Search.route) { SearchScreen(viewModel, navController) }
        composable(HomeSections.WishList.route) { WishListScreen() }
        composable(HomeSections.Reservation.route) { ReservationScreen() }

    }
    composable(route = Destinations.calendar) { DateScreen(navController = navController) }
    composable(route = Destinations.fare) { FareScreen(navController = navController) }
    composable(route = Destinations.personnel) { PersonnelScreen(navController = navController) }
    composable(route = Destinations.searchResult) { SearchResultScreen(navController = navController) }
    composable(route = Destinations.searchMap) { SearchMapScreen(navController = navController) }
    composable(route = Destinations.searchCondition) { SearchConditionScreen(navController = navController) }
    composable(route = Destinations.detail) { DetailScreen(navController = navController) }
}

fun navigateToCalendar(location: Travel, navController: NavController, from: NavBackStackEntry) {
    if (from.lifecycle.currentState == Lifecycle.State.RESUMED) {
        navController.navigate(Destinations.calendar)
    }
}

private fun selectNavigation(
    currentRoute: String?,
    item: HomeSections,
): Boolean {
    return when (item.route) {
        "검색" -> currentRoute == Destinations.searchResult
        else -> false
    }
}