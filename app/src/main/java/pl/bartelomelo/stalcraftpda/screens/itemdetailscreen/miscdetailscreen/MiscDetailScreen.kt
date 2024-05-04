package pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.miscdetailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pl.bartelomelo.stalcraftpda.data.remote.responses.test.ItemTest
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemDescriptionSection
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemTopSection
import pl.bartelomelo.stalcraftpda.ui.theme.LettersGray
import pl.bartelomelo.stalcraftpda.util.parseTypeToColor

@Composable
fun MiscScreen(item: ItemTest) {
    Column {
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            ItemTopSection(item = item)
        }
        val boxWeight = if (item.infoBlocks[item.infoBlocks.lastIndex].type == "list") 0.6f else 0.3f
        Box(
            modifier = Modifier
                .weight(boxWeight)
        ) {
            MiscInfoSection(item = item)
        }
        Box(
            modifier =  Modifier
                .weight(2.5f)
        ) {
            ItemDescriptionSection(properties = item.infoBlocks, index = item.infoBlocks.lastIndex)
        }
    }
}

@Composable
fun MiscInfoSection(item: ItemTest) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(Color.DarkGray)
        )
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            Column {
                LazyColumn {
                    items(item.infoBlocks[0].elements.size) {
                        Row {
                            when (item.infoBlocks[0].elements[it].type) {
                                "key-value" -> {
                                    Text(
                                        modifier = Modifier
                                            .weight(1.5f)
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
                                            .weight(1f)
                                            .padding(end = 3.dp),
                                        text = item.infoBlocks[0].elements[it].value.toString()
                                            .split("=")[6].let { text ->
                                            text.substring(0, text.length - 2) },
                                        textAlign = TextAlign.End,
                                        color = color
                                    )
                                }
                                "numeric" -> {
                                    Text(
                                        modifier = Modifier
                                            .weight(1.5f)
                                            .padding(start = 3.dp),
                                        text = item.infoBlocks[0].elements[it].name.lines.en,
                                        color = LettersGray
                                    )
                                    Text(
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(end = 3.dp),
                                        text = item.infoBlocks[0].elements[it].formatted.value.en,
                                        textAlign = TextAlign.End,
                                        color = LettersGray
                                    )
                                }
                            }
                        }
                    }
                }
                if (item.infoBlocks[item.infoBlocks.lastIndex].type == "list") {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.DarkGray)
                    )
                    LazyColumn {
                        items(item.infoBlocks[item.infoBlocks.lastIndex].elements.size) {
                            Text(
                                modifier = Modifier
                                    .padding(start = 3.dp),
                                text = item.infoBlocks[item.infoBlocks.lastIndex].elements[it].text.lines.en,
                                color = LettersGray
                            )
                        }
                    }
                }
            }
        }
    }
}
