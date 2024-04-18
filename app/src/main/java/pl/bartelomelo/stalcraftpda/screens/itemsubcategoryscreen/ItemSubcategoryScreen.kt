package pl.bartelomelo.stalcraftpda.screens.itemsubcategoryscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pl.bartelomelo.stalcraftpda.data.remote.responses.ItemList
import pl.bartelomelo.stalcraftpda.data.remote.responses.ItemListItem
import pl.bartelomelo.stalcraftpda.util.Resource
import pl.bartelomelo.stalcraftpda.util.StalcraftButton

@Composable
fun ItemSubcategoryScreen(
    navController: NavController,
    route: List<String>
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(39, 39, 47)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {
            ItemSubcategoryList(navController = navController, route = route)
        }
    }
}

@Composable
fun ItemSubcategoryEntry(
    entry: ItemListItem,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(StalcraftButton())
            .border(width = 1.dp, color = Color.White, StalcraftButton())
            .background(Color(55, 55, 64))
            .clickable {

            }
    ) {
        Column {
            entry.itemName?.let {
                Text(
                    text = it,
                    color = Color.White
                )
            } ?: Text(
                text = entry.name,
                color = Color.White
            )
        }
    }
}


@Composable
fun ItemSubcategoryList(
    navController: NavController,
    viewModel: ItemSubcategoryViewModel = hiltViewModel(),
    route: List<String>
) {
    val itemList = produceState<Resource<ItemList>>(initialValue = Resource.Loading()) {
        value = viewModel.getItemSubcategoryList(route[0], route[1])
    }.value

    if (itemList is Resource.Success) {
        LazyColumn(contentPadding = PaddingValues(15.dp)) {
            items(itemList.data!!.size) {
                ItemSubcategoryRow(
                    rowIndex = it,
                    entries = itemList.data,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun ItemSubcategoryRow(
    rowIndex: Int,
    entries: List<ItemListItem>,
    navController: NavController
) {
    Column {
        Row {
            ItemSubcategoryEntry(
                entry = entries[rowIndex],
                navController = navController,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
    }
}