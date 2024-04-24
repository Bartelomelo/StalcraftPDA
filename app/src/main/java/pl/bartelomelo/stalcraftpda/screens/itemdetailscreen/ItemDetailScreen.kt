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
import androidx.compose.foundation.lazy.LazyColumn
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
        if (item is Resource.Success) {
            when (item.data?.category!!.split("/")[0]) {
                "armor" -> {
                    ArmorScreen(item = item.data)
                }
            }
        }
    }
}

@Composable
fun ArmorScreen(item: ItemTest) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Box(
            modifier = Modifier
                .weight(2f)
        ) {
            ItemTopSection(item = item)
        }
        Box(
            modifier = Modifier
                .weight(2f)
        ) {
            ItemInfoSection(item = item)
        }
        Box(
            modifier = Modifier
                .weight(3f)
                .background(Color.Green)
        ) {
            Text(text = "ASDASD")
        }

    }
}

@Composable
fun ItemTopSection(
    item: ItemTest
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
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
    }
}

@Composable
fun ItemInfoSection(item: ItemTest) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(Color.DarkGray)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
                .weight(2f)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.TopStart
            ) {
                LazyColumn() {
                    items(item.infoBlocks[0].elements.size) {
                        when (item.infoBlocks[0].elements[it].type) {
                            "key-value" -> {
                                Text(
                                    modifier = Modifier.padding(start = 3.dp),
                                    text = item.infoBlocks[0].elements[it].key.lines.en,
                                    color = Color.White
                                )
                            }

                            "numeric" -> {
                                Text(
                                    modifier = Modifier.padding(start = 3.dp),
                                    text = item.infoBlocks[0].elements[it].name.lines.en,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Red),
                contentAlignment = Alignment.TopEnd
            ) {
                LazyColumn() {
                    items(item.infoBlocks[0].elements.size) {
                        when (item.infoBlocks[0].elements[it].type) {
                            "key-value" -> {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 5.dp),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Text(
                                        text = item.infoBlocks[0].elements[it].value.toString()
                                            .split("=")[6].let { value ->
                                            value.substring(0, value.length - 2)
                                        },
                                        color = Color.White
                                    )
                                }
                            }

                            "numeric" -> {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 5.dp),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Text(
                                        text = item.infoBlocks[0].elements[it].formatted.value.en,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.DarkGray)
        )
        Row(
            modifier = Modifier
                .weight(1.3f)
                .fillMaxWidth()

        ) {
            Text(text = "ASDSAD")
        }
    }
}
