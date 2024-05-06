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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import pl.bartelomelo.stalcraftpda.data.remote.responses.test.Element
import pl.bartelomelo.stalcraftpda.data.remote.responses.test.InfoBlock
import pl.bartelomelo.stalcraftpda.data.remote.responses.test.ItemTest
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.armordetailscreen.ArmorScreen
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.artefactdetailscreen.ArtefactScreen
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.containerdetailscreen.ContainerScreen
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.grenadedetailscreen.GrenadeScreen
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.medicinedetailscreen.MedicineScreen
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.miscdetailscreen.MiscScreen
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.weapondetailscreen.WeaponScreen
import pl.bartelomelo.stalcraftpda.ui.theme.BackgroundColor
import pl.bartelomelo.stalcraftpda.ui.theme.BackgroundSecondary
import pl.bartelomelo.stalcraftpda.ui.theme.LettersGray
import pl.bartelomelo.stalcraftpda.ui.theme.RankGreen
import pl.bartelomelo.stalcraftpda.util.Constants
import pl.bartelomelo.stalcraftpda.util.Resource
import pl.bartelomelo.stalcraftpda.util.parseTypeToColor

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
        color = BackgroundColor
    ) {
        if (item is Resource.Success) {
            when (item.data?.category!!.split("/")[0]) {
                "armor" -> ArmorScreen(item = item.data)
                "weapon" -> WeaponScreen(item = item.data)
                "artefact" -> ArtefactScreen(item = item.data)
                "misc" -> MiscScreen(item = item.data)
                "medicine" -> MedicineScreen(item = item.data)
                "grenade" -> GrenadeScreen(item = item.data)
                "containers" -> ContainerScreen(item = item.data)
            }
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
                    .weight(1.5f),
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
                            .fillMaxWidth(0.95f)
                            .background(Color.White)
                    )
                    Text(
                        text = itemName,
                        fontSize = 20.sp,
                        color = parseTypeToColor(item.color)
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
        val infoWeight = when (item.category) {
            "medicine", "grenade" -> 0.3f
            else -> 2.3f
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 3.dp, end = 3.dp)
                .weight(infoWeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LazyColumn {
                items(item.infoBlocks[0].elements.size) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                    ) {
                        when (item.infoBlocks[0].elements[it].type) {
                            "key-value" -> {
                                Text(
                                    modifier = Modifier
                                        .weight(1f),
                                    text = item.infoBlocks[0].elements[it].key.lines.en,
                                    color = LettersGray
                                )
                                val color = when (item.infoBlocks[0].elements[it].key.lines.en) {
                                    "Rank" -> parseTypeToColor(item.color)
                                    else -> parseTypeToColor("DEFAULT")
                                }
                                Text(
                                    modifier = Modifier
                                        .weight(2f),
                                    text = item.infoBlocks[0].elements[it].value.toString()
                                        .split("=")[6].let { value ->
                                        value.substring(0, value.length - 2)
                                    },
                                    color = color,
                                    textAlign = TextAlign.End
                                )
                            }
                            "numeric" -> {
                                Text(
                                    modifier = Modifier
                                        .weight(1f),
                                    text = item.infoBlocks[0].elements[it].name.lines.en,
                                    color = LettersGray
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(1f),
                                    text = item.infoBlocks[0].elements[it].formatted.value.en,
                                    color = LettersGray,
                                    textAlign = TextAlign.End
                                )
                            }
                        }
                    }
                }
            }
        }
        var rowWeight = 0.9f
        if (item.infoBlocks[2].type == "list" && item.infoBlocks[2].elements.isNotEmpty()  && item.category != "containers") {
            if (item.category.split("/")[0] != "artefact") {
                if (item.category != "medicine") {
                    rowWeight = when (item.infoBlocks[2].elements.size) {
                        1 -> 0.6f
                        2 -> 1f
                        3 -> 1.4f
                        4 -> 1.8f
                        6 -> 2.75f
                        else -> 2.3f
                    }
                } else {
                    rowWeight = when (item.infoBlocks[2].elements.size) {
                        1 -> 0.2f
                        2 -> 0.3f
                        3 -> 0.5f
                        4 -> 0.6f
                        6 -> 2.75f
                        else -> 2.3f
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
                    .weight(rowWeight)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LazyColumn {
                    items(item.infoBlocks[2].elements.size) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 3.dp, end = 3.dp)
                        ) {
                            when (item.infoBlocks[2].elements[it].type) {
                                "numeric" -> {
                                    val color =
                                        android.graphics.Color.parseColor("#${item.infoBlocks[2].elements[it].formatted.nameColor}")
                                    Text(
                                        modifier = Modifier
                                            .weight(1.75f),
                                        text = item.infoBlocks[2].elements[it].name.lines.en,
                                        color = Color(color)
                                    )
                                    Text(
                                        modifier = Modifier
                                            .weight(2f),
                                        text = item.infoBlocks[2].elements[it].formatted.value.en,
                                        textAlign = TextAlign.End,
                                        color = Color(color)
                                    )
                                }

                                "text" -> {
                                    Text(
                                        modifier = Modifier
                                            .weight(1f),
                                        text = item.infoBlocks[2].elements[it].text.lines.en,
                                        color = RankGreen
                                    )
                                }

                                else -> {
                                    val color =
                                        if (item.category == "medicine") LettersGray else Color.White
                                    Text(
                                        modifier = Modifier
                                            .weight(1f),
                                        text = item.infoBlocks[2].elements[0].key.lines.en,
                                        color = color
                                    )
                                    Text(
                                        modifier = Modifier
                                            .weight(2f),
                                        text = item.infoBlocks[2].elements[0].value.toString()
                                            .split("=")[6].let { value ->
                                            value.substring(0, value.length - 2)
                                        },
                                        textAlign = TextAlign.End,
                                        color = color
                                    )
                                }
                            }
                        }
                    }
                }
            }
            if (
                item.category.split("/").let { it[it.lastIndex] } == "melee" &&
                item.infoBlocks[3].elements.isNotEmpty() &&
                item.infoBlocks[3].elements.first().name.key.split(".")[3] == "speed_modifier"
            ) {
                val weight = when (item.infoBlocks[3].elements.size) {
                    1 -> 0.5f
                    2 -> 0.8f
                    else -> 1f
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.DarkGray)
                )
                Row(
                    modifier = Modifier
                        .weight(weight)
                        .fillMaxWidth()
                ) {
                    LazyColumn {
                        items(item.infoBlocks[3].elements.size) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 3.dp, end = 3.dp)
                            ) {
                                val color =
                                    android.graphics.Color.parseColor("#${item.infoBlocks[3].elements[it].formatted.nameColor}")
                                Text(
                                    modifier = Modifier
                                        .weight(1f),
                                    text = item.infoBlocks[3].elements[it].name.lines.en,
                                    color = Color(color)
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(1f),
                                    text = item.infoBlocks[3].elements[it].formatted.value.en,
                                    textAlign = TextAlign.End,
                                    color = Color(color)
                                )
                            }
                        }
                    }
                }
            }
        } else if (item.infoBlocks[2].type == "text") {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.DarkGray)
            )
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 3.dp),
                    text = item.infoBlocks[2].text.lines.en,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun ItemPropertiesSection(properties: List<Element>) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(Color.DarkGray)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LazyColumn(userScrollEnabled = false) {
                items(properties.size) {
                    val backgroundColor = when {
                        it % 2 == 0 -> BackgroundSecondary
                        else -> BackgroundColor
                    }
                    val rowHeight =
                        if (properties.size < 10) 30.dp else (360 / properties.size).dp
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(rowHeight)
                            .background(backgroundColor),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        when (properties[it].type) {
                            "key-value" -> {
                                Text(
                                    modifier = Modifier
                                        .weight(2f)
                                        .padding(start = 3.dp, end = 3.dp),
                                    text = properties[it].key.lines.en,
                                    color = LettersGray
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 3.dp, end = 3.dp),
                                    text = properties[it].value.toString()
                                        .split("=")[6].let { value ->
                                        value.substring(0, value.length - 2)
                                    },
                                    textAlign = TextAlign.End,
                                    color = LettersGray
                                )
                            }

                            "numeric" -> {
                                val color =
                                    android.graphics.Color.parseColor("#${properties[it].formatted.nameColor}")
                                Text(
                                    modifier = Modifier
                                        .weight(2f)
                                        .padding(start = 3.dp, end = 3.dp),
                                    text = properties[it].name.lines.en,
                                    color = LettersGray
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 3.dp, end = 3.dp),
                                    text = properties[it].formatted.value.en,
                                    textAlign = TextAlign.End,
                                    color = Color(color)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemDescriptionSection(properties: List<InfoBlock>, index: Int) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(Color.DarkGray)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            if (properties[index].type != "text") {
                Text(
                    modifier = Modifier
                        .padding(3.dp),
                    text = "There is nothing to say about this item.",
                    textAlign = TextAlign.Start,
                    color = LettersGray
                )
            } else if (properties[index].text.lines.en == "") {
                Text(
                    modifier = Modifier
                        .padding(3.dp),
                    text = "There is nothing to say about this item.",
                    textAlign = TextAlign.Start,
                    color = LettersGray
                )
            } else {
                Text(
                    modifier = Modifier
                        .padding(3.dp),
                    text = properties[index].text.lines.en,
                    textAlign = TextAlign.Start,
                    color = LettersGray
                )
            }
        }
    }
}




