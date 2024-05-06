package pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.bulletdetailscreen

import android.util.Log
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
import pl.bartelomelo.stalcraftpda.data.remote.responses.test.InfoBlock
import pl.bartelomelo.stalcraftpda.data.remote.responses.test.ItemTest
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemInfoSection
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemPropertiesSection
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemTopSection
import pl.bartelomelo.stalcraftpda.ui.theme.LettersGray

@Composable
fun BulletScreen(item: ItemTest) {
    Column {
        Box(
            modifier = Modifier
                .weight(2f)
        ){
            ItemTopSection(item = item)
        }
        Box(
            modifier = Modifier
                .weight(0.8f)
        ){
            ItemInfoSection(item = item)
        }
        val itemSize = item.infoBlocks.size
        Log.d("size", itemSize.toString())
        if (itemSize == 5) {
            Box(
                modifier = Modifier
                    .weight(0.2f)
            ){
                BulletProjectivesSection(properties = item.infoBlocks[3])
            }
        }
        Box(
            modifier = Modifier
                .weight(2f)
        ){
            ItemPropertiesSection(properties = item.infoBlocks[item.infoBlocks.lastIndex].elements)
        }
    }
}

@Composable
fun BulletProjectivesSection(properties: InfoBlock) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.DarkGray)
        )
        LazyColumn {
            items(properties.elements.size) {
                Row {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 3.dp),
                        text = properties.elements[it].name.lines.en,
                        color = LettersGray
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 3.dp),
                        text = properties.elements[it].formatted.value.en,
                        textAlign = TextAlign.End,
                        color = Color.White
                    )
                }
            }
        }
    }
}