import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todo.airbnb.data.Travel
import com.example.todo.airbnb.presentation.main.components.MainAppBar
import com.example.todo.airbnb.presentation.search.SearchViewModel
import com.example.todo.airbnb.presentation.search.SearchWidgetState
import com.example.todo.airbnb.presentation.search.components.*
import com.example.todo.airbnb.presentation.search.components.navigation.SearchNavGraph
import com.example.todo.airbnb.presentation.search.components.navigation.SearchScreens

@Composable
fun SearchScreen(viewModel: SearchViewModel) {
    SearchNavGraph(viewModel)
}

@Composable
fun SearchMainScreen(navController: NavController, viewModel: SearchViewModel) {
    val searchWidgetState by viewModel.searchWidgetState
    val searchTextState by viewModel.searchTextState
    val travelLocations = viewModel.travelLocation.collectAsState().value
    val searchLocations = viewModel.searchLocations.collectAsState().value
    Scaffold(
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = { viewModel.updateSearchText(newValue = it) },
                onCloseClicked = { viewModel.updateSearchText(newValue = "") },
                onSearchClicked = { viewModel.getSearchLocations(searchTextState) },
                onOpenTriggered = { viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPEN) },
                onCloseTriggered = { viewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED) },
            )
        }
    ) {
        when (searchWidgetState) {
            is SearchWidgetState.CLOSED -> {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    LoadMainImage()
                    TravelScreen(viewModel = viewModel)
                    AccommodationsScreen(viewModel = viewModel)
                }
            }
            is SearchWidgetState.OPEN -> {
                when {
                    searchTextState.isEmpty() -> SearchList(navController, travelLocations)
                    else -> SearchList(navController, searchLocations)
                }
            }
        }
    }

}

@Composable
private fun SearchList(navController: NavController, travelLocations: List<Travel>) {
    LazyColumn(
        modifier = Modifier
            .padding(start = 16.dp, bottom = 60.dp, top = 32.dp, end = 16.dp)
    ) {
        if (!travelLocations.isNullOrEmpty()) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    text = "근처의 인기 여행지",
                    style = MaterialTheme.typography.h6
                )
            }
            items(travelLocations) { location ->
                Row(
                    modifier = Modifier
                        .clickable { navController.navigate(SearchScreens.Date.route) }
                        .fillMaxWidth()
                ) { MakeItem(location = location) }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}