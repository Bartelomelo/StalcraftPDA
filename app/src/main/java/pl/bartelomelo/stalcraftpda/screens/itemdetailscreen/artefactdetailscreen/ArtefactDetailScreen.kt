package pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.artefactdetailscreen

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pl.bartelomelo.stalcraftpda.data.remote.responses.test.InfoBlock
import pl.bartelomelo.stalcraftpda.data.remote.responses.test.ItemTest
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemDescriptionSection
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemInfoSection
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemTopSection
import pl.bartelomelo.stalcraftpda.ui.theme.LettersGray

@Composable
fun ArtefactScreen(item: ItemTest) {
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
                .weight(0.9f)
        ) {
            ItemInfoSection(item = item)
        }
        Box(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth()
        ) {
            ArtefactInfoSection(properties = item)
        }
        val boxWeight = when (item.infoBlocks[4].elements.size) {
            3 -> 0.9f
            else -> 1.2f
        }
        Box(
            modifier = Modifier
                .weight(boxWeight)
        ) {
            ArtefactPropertiesSection(properties = item.infoBlocks[4])
        }
        Box(
            modifier = Modifier
                .weight(2.5f)
        ){
            ItemDescriptionSection(properties = item.infoBlocks, index = item.infoBlocks.lastIndex)
        }
    }
}

@Composable
fun ArtefactInfoSection(properties: ItemTest) {
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
                .weight(5f)
        ) {
            LazyColumn {
                items(properties.infoBlocks[3].elements.size) {
                    Row {
                        when (properties.infoBlocks[3].elements[it].type) {
                            "key-value" -> {
                                Text(
                                    modifier = Modifier
                                        .weight(2f)
                                        .padding(start = 3.dp),
                                    text = properties.infoBlocks[3].elements[it].key.lines.en,
                                    color = LettersGray
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(end = 3.dp),
                                    text = properties.infoBlocks[3].elements[it].value.toString()
                                        .split("=")[2].let { value ->
                                        value.substring(0, value.length - 1)
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
                                    text = properties.infoBlocks[3].elements[it].name.lines.en,
                                    color = LettersGray
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(end = 3.dp),
                                    text = properties.infoBlocks[3].elements[it].formatted.value.en,
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
fun ArtefactPropertiesSection(properties: InfoBlock) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.DarkGray)
        )
        LazyColumn(
            modifier = Modifier.padding(bottom = 3.dp)
        ) {
            items(properties.elements.size) {
                Row(
                    modifier = Modifier
                        .padding(start = 3.dp, end = 3.dp, top = 3.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = properties.elements[it].name.lines.en,
                        color = LettersGray
                    )
                    val color = android.graphics.Color.parseColor("#${properties.elements[it].formatted.nameColor}")
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = properties.elements[it].formatted.value.en,
                        color = Color(color),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}