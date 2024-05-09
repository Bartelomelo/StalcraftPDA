package pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.armordetailscreen

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
        val itemInfoWeight = when (item.infoBlocks[2].elements.size) {
            0 -> 1.1f
            1 -> 1.3f
            2 -> 1.65f
            3 -> 2f
            4 -> 2.35f
            else -> 2.7f
        }
        Box(
            modifier = Modifier
                .weight(itemInfoWeight)
        ) {
            ItemInfoSection(item = item)
        }
        Box(
            modifier = Modifier
                .weight(3f)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            Column {
                val boxSize = item.infoBlocks[3].elements.size * 30
                Box(
                    modifier = Modifier
                        .height(boxSize.dp)
                        .fillMaxWidth(),
                ) {
                    ArmorPropertiesSection(properties = item.infoBlocks[3].elements)
                }
                Box(
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                ) {
                    ArmorCompatibilitySection(properties = item.infoBlocks)
                }
                Box(
                    modifier = Modifier
                        .height(115.dp)
                        .fillMaxWidth()
                ) {
                    ItemDescriptionSection(properties = item.infoBlocks, 6)
                }
            }
        }
    }
}

@Composable
fun ArmorDeviceScreen(item: ItemTest) {
    Column {
        Box(
            modifier = Modifier
                .weight(1.5f)
        ) {
            ItemTopSection(item = item)
        }
        val infoWeight = when (item.infoBlocks[0].elements.size) {
            2 -> 0.5f
            else -> 0.7f
        }
        Box(
            modifier = Modifier
                .weight(infoWeight)
        ) {
            ItemInfoSection(item = item)
        }
        Box(
            modifier = Modifier
                .weight(4f)
        ) {
            ItemDescriptionSection(properties = item.infoBlocks, index = item.infoBlocks.lastIndex)
        }
    }
}

@Composable
fun ArmorPropertiesSection(properties: List<Element>) {
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
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                            .background(backgroundColor)
                    ) {
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

@Composable
fun ArmorCompatibilitySection(properties: List<InfoBlock>) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
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
                Text(
                    text = properties[4].text.lines.en,
                    color = Color.White
                )
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
                    text = properties[5].title.lines.en,
                    color = LettersGray
                )
                Text(
                    text = properties[5].text.lines.en,
                    color = Color.White
                )
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