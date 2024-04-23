package pl.bartelomelo.stalcraftpda.screens.itemdetailscreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import pl.bartelomelo.stalcraftpda.data.remote.responses.test.ItemTest
import pl.bartelomelo.stalcraftpda.util.Constants
import pl.bartelomelo.stalcraftpda.util.Resource

@Composable
fun ItemDetailScreen(
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
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            if (item is Resource.Success) {
//                val name = item.data?.name?.lines?.en
//                val categoryName = item.data?.infoBlocks?.get(0)?.elements?.get(0)?.key?.lines?.en
//                val itemName = item.data?.infoBlocks?.get(0)?.elements?.get(0)?.value
//                val className = itemName.toString().split("=")[6].let {
//                    it.substring(0, it.length - 2)
//                }
//                Text(text = "$categoryName: $className Name: $name")
                Box() {
                    ItemTopSection(item = item.data!!)
                }
                Box() {
                    ItemInfoSection(item = item.data!!)
                }
            }

        }
    }
}

@Composable
fun ItemTopSection(
    item: ItemTest
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                contentAlignment = Alignment.TopStart
            ) {
                val className = item.infoBlocks[0].elements[0].value.toString().split("=")[6].let {
                    it.substring(0, it.length - 2)
                }
                val itemName = item.name.lines.en.split(" ")[0]
                Column(
                    modifier = Modifier.padding(start = 5.dp, top = 10.dp)
                ) {
                    Text(
                        text = className,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth(0.9f)
                            .background(Color.White)
                    )
                    Text(
                        text = itemName,
                        fontSize = 20.sp,
                        color = Color.Red
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f),
                contentAlignment = Alignment.Center
            ) {
                Log.d("image", "${Constants.IMAGE_URL}${item.category}/${item.id}.png")
                AsyncImage(
                    model = "${Constants.IMAGE_URL}${item.category}/${item.id}.png",
                    modifier = Modifier
                        .fillMaxSize(0.7f),
                    contentDescription = null
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .background(Color.DarkGray)
        )
    }
}

@Composable
fun ItemInfoSection(item: ItemTest) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.Green)
        ) {

        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.Cyan)
        ) {

        }
    }
}