package pl.bartelomelo.stalcraftpda.screens.itemdetailscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pl.bartelomelo.stalcraftpda.data.remote.responses.test.ItemTest
import pl.bartelomelo.stalcraftpda.util.Resource

@Composable
fun ItemDetailScreen(
    navController: NavController,
    route: List<String?>,
    viewModel: ItemDetailViewModel = hiltViewModel()
) {

    val item = produceState<Resource<ItemTest>>(initialValue = Resource.Loading()) {
        value = viewModel.getItemDetail(route[0]!!, route[1], route[2]!!)
    }.value
    
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
            if (item is Resource.Success) {
                val name = item.data?.name?.lines?.en
                val key = item.data?.infoBlocks?.get(0)?.elements?.get(0)?.key?.lines?.en
                val itemName = item.data?.infoBlocks?.get(0)?.elements?.get(0)?.value
                val className = itemName.toString().split("=")[6].let {
                    it.substring(0, it.length - 2)
                }
                Text(text = "$key: $className Name: $name")
            }

        }
    }
}