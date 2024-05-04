package pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.medicinedetailscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.bartelomelo.stalcraftpda.data.remote.responses.test.ItemTest
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemDescriptionSection
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemInfoSection
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemPropertiesSection
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemTopSection

@Composable
fun MedicineScreen(item: ItemTest) {
    Column {
        Box(
            modifier = Modifier
                .weight(2f)
        ) {
            ItemTopSection(item = item)
        }
        Box(
            modifier = Modifier
                .weight(1.5f)
        ) {
            ItemInfoSection(item = item)
        }
        val boxWeight = when (item.infoBlocks[3].elements.size) {
            1 -> 0.4f
            2 -> 0.7f
            3 -> 1.1f
            4 -> 1.5f
            else -> 2f
        }
        Box(
            modifier = Modifier
                .weight(boxWeight)
        ) {
            ItemPropertiesSection(properties = item.infoBlocks[3].elements)
        }
        Box(
            modifier = Modifier
                .weight(3f)
        ) {
            ItemDescriptionSection(properties = item.infoBlocks, index = item.infoBlocks.lastIndex)
        }
    }    
}

