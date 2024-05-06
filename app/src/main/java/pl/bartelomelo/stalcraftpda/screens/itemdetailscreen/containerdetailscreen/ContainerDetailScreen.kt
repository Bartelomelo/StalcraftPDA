package pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.containerdetailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pl.bartelomelo.stalcraftpda.data.remote.responses.test.ItemTest
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemDescriptionSection
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemInfoSection
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemPropertiesSection
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemTopSection

@Composable
fun ContainerScreen(item: ItemTest) {
    Column {
        Box(
            modifier = Modifier
                .weight(1.5f)
        ) {
            ItemTopSection(item = item)
        }
        val infoWeight = if (item.infoBlocks[2].type == "text") 0.7f else 0.55f
        Box(
            modifier = Modifier
                .weight(infoWeight)
        ) {
            ItemInfoSection(item = item)
        }
        val propertiesWeight = if (item.infoBlocks[2].type == "text") {
            when (item.infoBlocks[3].elements.size) {
                2 -> 0.5f
                else -> 0.7f
            }
        } else {
            when (item.infoBlocks[2].elements.size) {
                2 -> 0.5f
                else -> 0.7f
            }
        }
        Box(
            modifier = Modifier
                .weight(propertiesWeight)
        ) {
            if (item.infoBlocks[2].type == "text") {
                ItemPropertiesSection(properties = item.infoBlocks[3].elements)
            } else {
                ItemPropertiesSection(properties = item.infoBlocks[2].elements)
            }
        }
        Box(
            modifier = Modifier
                .weight(2f)
        ) {
                        Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.DarkGray)
            )
            ItemDescriptionSection(properties = item.infoBlocks, index = item.infoBlocks.lastIndex)
        }
    }
}