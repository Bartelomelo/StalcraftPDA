package pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.grenadedetailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemPropertiesSection
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemTopSection
import pl.bartelomelo.stalcraftpda.ui.theme.LettersGray

@Composable
fun GrenadeScreen(item: ItemTest) {
    Column {
        Box(
            modifier = Modifier
                .weight(2f)
        ) {
            ItemTopSection(item = item)
        }
        val isText = item.infoBlocks[2].type == "text"
        val infoWeight = if (isText) 1f else 0.5f
        Box(
            modifier = Modifier
                .weight(infoWeight)
        ) {
            GrenadeInfoSection(properties = item)
        }
        var boxWeight = 2f
        val isList = item.infoBlocks[2].type == "list"
        if (isList) {
            boxWeight = when (item.infoBlocks[2].elements.size) {
                1 -> 0.4f
                2 -> 0.7f
                3 -> 0.9f
                4 -> 1.5f
                5 -> 1.7f
                else -> 2f
            }
        }
        Box(
            modifier = Modifier
                .weight(boxWeight)
        ) {
            if (isList) {
                ItemPropertiesSection(properties = item.infoBlocks[2].elements)
            } else {
                ItemPropertiesSection(properties = item.infoBlocks[3].elements)
            }

        }
        Box(
            modifier = Modifier
                .weight(2.5f)
        ) {
            ItemDescriptionSection(properties = item.infoBlocks, index = item.infoBlocks.lastIndex)
        }
    }
}

@Composable
fun GrenadeInfoSection(properties: ItemTest) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.DarkGray)
        )
        Box(
            modifier = Modifier
                .weight(2f)
        ) {
            LazyColumn {
                items(properties.infoBlocks[0].elements.size) {
                    Row {
                        when (properties.infoBlocks[0].elements[it].type) {
                            "key-value" -> {
                                Text(
                                    modifier = Modifier
                                        .weight(2f)
                                        .padding(start = 3.dp),
                                    text = properties.infoBlocks[0].elements[it].key.lines.en,
                                    color = LettersGray
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(end = 3.dp),
                                    text = properties.infoBlocks[0].elements[it].value.toString()
                                        .split("=")[6].let { value ->
                                        value.substring(0, value.length - 2)
                                    },
                                    color = LettersGray,
                                    textAlign = TextAlign.End
                                )
                            }

                            "numeric" -> {
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 3.dp),
                                    text = properties.infoBlocks[0].elements[it].name.lines.en,
                                    color = LettersGray
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(end = 3.dp),
                                    text = properties.infoBlocks[0].elements[it].formatted.value.en,
                                    color = LettersGray,
                                    textAlign = TextAlign.End
                                )
                            }
                        }
                    }
                }
            }
        }
        if (properties.infoBlocks[2].type == "text") {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.DarkGray)
            )
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(3.dp)
            ) {
                Text(
                    text = properties.infoBlocks[2].text.lines.en,
                    color = Color.White
                )
            }
        }
    }
}