package pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.gundetailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pl.bartelomelo.stalcraftpda.data.remote.responses.test.Element
import pl.bartelomelo.stalcraftpda.data.remote.responses.test.InfoBlock
import pl.bartelomelo.stalcraftpda.data.remote.responses.test.ItemTest
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemDescriptionSection
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemInfoSection
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemTopSection
import pl.bartelomelo.stalcraftpda.ui.theme.BackgroundColor
import pl.bartelomelo.stalcraftpda.ui.theme.BackgroundSecondary
import pl.bartelomelo.stalcraftpda.ui.theme.LettersGray
import pl.bartelomelo.stalcraftpda.util.parseTypeToColor

@Composable
fun WeaponScreen(item: ItemTest) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Box(
            modifier = Modifier
                .weight(1.5f)
        ) {
            ItemTopSection(item = item)
        }
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            when (item.category.split("/")[1]) {
                "melee" -> ItemInfoSection(item = item)
                else -> WeaponInfoSection(item = item)
            }
        }
        Box(
            modifier = Modifier
                .weight(3.2f)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .height(390.dp)
                        .fillMaxWidth(),
                ) {
                    WeaponPropertiesSection(properties = item.infoBlocks[2].elements)
                }
                val boxHeight = when (item.infoBlocks[3].elements.size) {
                    0 -> 80.dp
                    1 -> 140.dp
                    2 -> 170.dp
                    3 -> 190.dp
                    else -> 225.dp
                }
                Box(
                    modifier = Modifier
                        .height(boxHeight)
                        .fillMaxWidth()
                ) {
                    WeaponModifierSection(properties = item.infoBlocks)
                }
                Box(
                    modifier = Modifier
                        .height(125.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Diagram")
                }
                if (item.id != "y3jj0") {
                    Box(
                        modifier = Modifier
                            .height(125.dp)
                            .fillMaxWidth()
                    ) {
                        when (item.category.split("/")[1]) {
                            "melee" -> ItemDescriptionSection(
                                properties = item.infoBlocks,
                                item.infoBlocks.lastIndex
                            )
                            else -> ItemDescriptionSection(properties = item.infoBlocks, 7)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WeaponInfoSection(item: ItemTest) {
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
                .weight(2f),
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
                                        .weight(1f)
                                        .padding(start = 3.dp),
                                    text = item.infoBlocks[0].elements[it].key.lines.en,
                                    color = LettersGray
                                )
                                val color = when (item.infoBlocks[0].elements[it].key.lines.en) {
                                    "Rank" -> parseTypeToColor(item.color)
                                    else -> parseTypeToColor("DEFAULT")
                                }
                                Text(
                                    modifier = Modifier
                                        .padding(end = 3.dp)
                                        .weight(1f),
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
                                        .weight(1f)
                                        .padding(start = 3.dp),
                                    text = item.infoBlocks[0].elements[it].name.lines.en,
                                    color = LettersGray
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(end = 3.dp),
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
    }
}

@Composable
fun WeaponPropertiesSection(properties: List<Element>) {
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
                    val rowHeight = 360 / properties.size
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(rowHeight.dp)
                            .background(backgroundColor)
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
                                    color = Color.White
                                )
                            }

                            "numeric" -> {
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
                                    color = Color.White
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
fun WeaponModifierSection(properties: List<InfoBlock>) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(Color.DarkGray)
        )
        if (properties[3].elements.isNotEmpty()) {
            val boxWeight = when (properties[3].elements.size) {
                1 -> 0.5f
                2 -> 0.8f
                3 -> 1f
                else -> 1.1f
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(boxWeight)
                    .padding(start = 3.dp, end = 3.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Column {
                    Text(
                        text = properties[3].title.lines.en,
                        color = LettersGray
                    )
                    LazyColumn {
                        items(properties[3].elements.size) {
                            val color =
                                android.graphics.Color.parseColor("#${properties[3].elements[it].formatted.nameColor}")
                            Row {
                                Text(
                                    modifier = Modifier
                                        .weight(2f),
                                    text = properties[3].elements[it].name.lines.en,
                                    color = LettersGray
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(1f),
                                    text = properties[3].elements[it].formatted.value.en,
                                    textAlign = TextAlign.End,
                                    color = Color(color)
                                )
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 3.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                Text(
                    text = properties[4].title.lines.en,
                    color = LettersGray
                )
                LazyColumn {
                    items(properties[4].elements.size) {
                        Text(
                            text = properties[4].elements[it].text.lines.en,
                            color = LettersGray
                        )
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
    }
}
