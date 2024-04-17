package pl.bartelomelo.stalcraftpda.screens.itemcategoryscreen

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
fun ItemCategoryScreen(
    navController: NavController,
    route: String
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
            ItemCategoryList(navController = navController, route = route)
        }
    }
}

@Composable
fun ItemCategoryEntry(
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
                if (entry.download_url == null) {
                    navController.navigate("item_subcategory_screen")
                } else {
                    navController.navigate("item_detail_screen")
                }
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
fun ItemCategoryList(
    navController: NavController,
    viewModel: ItemCategoryViewModel = hiltViewModel(),
    route: String
) {
    val itemList = produceState<Resource<ItemList>>(initialValue = Resource.Loading()) {
        value = viewModel.getItemCategoryList(route)
    }.value

    if (itemList is Resource.Success) {
        LazyColumn(contentPadding = PaddingValues(15.dp)) {
            items(itemList.data!!.size) {
                ItemCategoryRow(
                    rowIndex = it,
                    entries = itemList.data,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun ItemCategoryRow(
    rowIndex: Int,
    entries: List<ItemListItem>,
    navController: NavController
) {
    Column {
        Row {
            ItemCategoryEntry(
                entry = entries[rowIndex],
                navController = navController,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
    }
}