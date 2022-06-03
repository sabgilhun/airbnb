package com.example.todo.airbnb.presentation.main.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import com.example.todo.airbnb.R
import com.example.todo.airbnb.presentation.main.components.Destinations.searchResult
import com.example.todo.airbnb.presentation.reservation.components.ReservationScreen
import com.example.todo.airbnb.presentation.search.SearchViewModel
import com.example.todo.airbnb.presentation.search.date.components.DateScreen
import com.example.todo.airbnb.presentation.search.detail.DetailScreen
import com.example.todo.airbnb.presentation.search.fare.components.FareScreen
import com.example.todo.airbnb.presentation.search.main.SearchScreen
import com.example.todo.airbnb.presentation.search.personnel.components.PersonnelScreen
import com.example.todo.airbnb.presentation.search.searchmap.SearchMapScreen
import com.example.todo.airbnb.presentation.search.searchresult.components.SearchResultScreen
import com.example.todo.airbnb.presentation.search.serachcondition.SearchConditionScreen
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
                    val backStackEntry = navController.previousBackStackEntry
                    val route = backStackEntry?.destination?.route
                    if (item.route == "검색" && route == searchResult) {
                        navController.navigate(route ?: item.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    } else {
                        navController.navigate(item.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@ExperimentalMaterialApi
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

@ExperimentalMaterialApi
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

    composable(route = Destinations.calendar) {
        DateScreen(navController = navController, viewModel)
    }
    composable(route = Destinations.fare) { FareScreen(navController = navController, viewModel) }
    composable(route = Destinations.personnel) {
        PersonnelScreen(navController = navController, viewModel)
    }
    composable(route = Destinations.searchResult) {
        SearchResultScreen(
            navController = navController,
            viewModel
        )
    }
    composable(route = Destinations.searchMap) { SearchMapScreen(navController = navController) }
    composable(route = Destinations.searchCondition) { SearchConditionScreen(navController = navController) }
    composable(route = Destinations.detail) { DetailScreen(navController = navController) }
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