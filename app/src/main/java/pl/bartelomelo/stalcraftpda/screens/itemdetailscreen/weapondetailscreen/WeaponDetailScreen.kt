package pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.weapondetailscreen

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.line.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.line.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import pl.bartelomelo.stalcraftpda.data.remote.responses.test.InfoBlock
import pl.bartelomelo.stalcraftpda.data.remote.responses.test.ItemTest
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemDescriptionSection
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemInfoSection
import pl.bartelomelo.stalcraftpda.screens.itemdetailscreen.ItemPropertiesSection
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
        var itemInfoWeight = 1f
        if (item.category.split("/")[1] == "melee" && item.infoBlocks[3].elements.first().name.key.split(
                "."
            )[3] == "speed_modifier"
        ) {
            itemInfoWeight = when (item.infoBlocks[3].elements.size) {
                0 -> 1.1f
                1 -> 1.3f
                2 -> 1.65f
                else -> 2f
            }
        }
        Box(
            modifier = Modifier
                .weight(itemInfoWeight)
        ) {
            when (item.category.split("/")[1]) {
                "melee" -> ItemInfoSection(item = item)
                else -> WeaponInfoSection(item = item)
            }
        }
        Box(
            modifier = Modifier
                .weight(3f)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            Column {
                val weaponPropertiesHeight =
                    if (item.category.split("/")[1] == "melee") 255.dp else 390.dp
                Box(
                    modifier = Modifier
                        .height(weaponPropertiesHeight)
                        .fillMaxWidth(),
                ) {
                    when (item.category.split("/")[1]) {
                        "melee" -> {
                            if (item.infoBlocks[3].elements.first().name.key.split(".")[3] == "speed_modifier") {
                                ItemPropertiesSection(properties = item.infoBlocks[4].elements)
                            } else {
                                ItemPropertiesSection(properties = item.infoBlocks[3].elements)
                            }
                        }

                        else -> ItemPropertiesSection(properties = item.infoBlocks[2].elements)
                    }
                }
                var boxHeight = 90.dp
                if (item.category.split("/")[1] != "melee") {
                    boxHeight = when (item.infoBlocks[3].elements.size) {
                        0 -> 90.dp
                        1 -> 140.dp
                        2 -> 170.dp
                        3 -> 190.dp
                        else -> 225.dp
                    }
                }
                Box(
                    modifier = Modifier
                        .height(boxHeight)
                        .fillMaxWidth()
                ) {
                    when (item.category.split("/")[1]) {
                        "melee" -> {
                            if (item.name.lines.en.lowercase().trim() == "razor") {
                                MeleeFeaturesSection(properties = item.infoBlocks[4])
                            } else if (item.infoBlocks[5].type == "list") {
                                MeleeFeaturesSection(properties = item.infoBlocks[5])
                            } else {
                                MeleeFeaturesSection(properties = item.infoBlocks[4])
                            }
                        }

                        else -> WeaponModifierSection(properties = item.infoBlocks)
                    }
                }
                if (item.category.split("/")[1] != "melee") {
                    Box(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                    ) {
                        WeaponDamageSection(item.infoBlocks[6])
                    }
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

                            else -> ItemDescriptionSection(
                                properties = item.infoBlocks,
                                item.infoBlocks.lastIndex
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WeaponDeviceScreen(item: ItemTest) {
    Column {
        Box(
            modifier = Modifier
                .weight(1.5f)
        ) {
            ItemTopSection(item = item)
        }
        val infoWeight = when (item.infoBlocks[0].elements.size) {
            5 -> 1.2f
            else -> 0.8f
        }
        Box(
            modifier = Modifier
                .weight(infoWeight)
        ) {
            ItemInfoSection(item = item)
        }
        val boxWeight = when (item.infoBlocks.size > 5) {
            true -> 0.35f
            false -> 0.5f
        }
        Box(
            modifier = Modifier
                .weight(boxWeight)
        ){
            WeaponDeviceSection(item = item)
        }
        Box(
            modifier = Modifier
                .weight(2f)
        ) {
            ItemDescriptionSection(properties = item.infoBlocks, index = item.infoBlocks.lastIndex)
        }
    }
}

@Composable
fun WeaponDeviceSection(item: ItemTest) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.DarkGray)
        )

        LazyColumn {
            items(item.infoBlocks[3].elements.size) {
                val backgroundColor = when {
                    it % 2 == 0 -> BackgroundColor
                    else -> BackgroundSecondary
                }
                Row (
                    modifier = Modifier
                        .background(backgroundColor)
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1.5f)
                            .padding(start = 3.dp),
                        text = item.infoBlocks[3].elements[it].name.lines.en,
                        color = LettersGray
                    )
                    Text(
                        modifier = Modifier
                            .weight(1.5f)
                            .padding(end = 3.dp),
                        text = item.infoBlocks[3].elements[it].formatted.value.en,
                        color = LettersGray,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
        if (item.infoBlocks[4].type == "list") {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.DarkGray)
            )
            LazyColumn {
                items(item.infoBlocks[4].elements.size) {
                    Row {
                        Text(
                            modifier = Modifier
                                .weight(1.5f)
                                .padding(start = 3.dp),
                            text = item.infoBlocks[4].elements[it].name.lines.en,
                            color = LettersGray
                        )
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 3.dp),
                            text = item.infoBlocks[4].elements[it].formatted.value.en,
                            color = LettersGray,
                            textAlign = TextAlign.End
                        )
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
                3 -> 1.1f
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
                .weight(1.2f)
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

@Composable
fun MeleeFeaturesSection(properties: InfoBlock) {
    Column {
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
                    text = properties.title.lines.en,
                    color = LettersGray
                )
                LazyColumn {
                    items(properties.elements.size) {
                        Text(
                            text = properties.elements[it].text.lines.en,
                            color = Color.White
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

@Composable
fun WeaponDamageSection(properties: InfoBlock) {
    val lineChartData = mutableListOf(
        LineChartData(
            points = listOf(
                LineChartData.Point(properties.startDamage.toFloat(), "0m"),
                LineChartData.Point(
                    properties.startDamage.toFloat(),
                    "${properties.damageDecreaseStart.split(".")[0]}m"
                ),
                LineChartData.Point(
                    properties.endDamage.toFloat(),
                    "${properties.damageDecreaseEnd.split(".")[0]}m"
                ),
                LineChartData.Point(
                    properties.endDamage.toFloat(),
                    "${properties.maxDistance.split(".")[0]}m"
                )
            ),
            lineDrawer = SolidLineDrawer(
                color = LettersGray
            ),
        )
    )
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.DarkGray)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(start = 3.dp, end = 3.dp, top = 3.dp)
        ) {
            Column {

                Text(
                    text = "Damage reduction at a distance",
                    color = LettersGray
                )
                Row {
                    Text(
                        modifier = Modifier.weight(2f),
                        text = "Up to ${properties.damageDecreaseStart.split(".")[0]} meters",
                        color = LettersGray
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "${properties.startDamage}unit(s)",
                        color = Color.White,
                        textAlign = TextAlign.End
                    )
                }
                Row {
                    Text(
                        modifier = Modifier.weight(2f),
                        text = "From ${properties.damageDecreaseEnd.split(".")[0]} to ${
                            properties.maxDistance.split(
                                "."
                            )[0]
                        } meters",
                        color = LettersGray
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "${properties.endDamage}unit(s)",
                        color = Color.White,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1.5f),
            contentAlignment = Alignment.CenterStart
        ) {
            LineChart(
                linesChartData = lineChartData,
                modifier = Modifier
                    .fillMaxHeight(0.9f)
                    .fillMaxWidth(0.95f),
                animation = simpleChartAnimation(),
                pointDrawer = FilledCircularPointDrawer(
                    color = Color.White
                ),
                horizontalOffset = 0f,
                yAxisDrawer = SimpleYAxisDrawer(
                    labelRatio = 1,
                    labelValueFormatter = { "" },
                    axisLineColor = Color.DarkGray
                ),
                xAxisDrawer = SimpleXAxisDrawer(
                    axisLineColor = Color.DarkGray,
                    labelRatio = 1,
                    labelTextColor = Color.DarkGray
                )
            )
        }
    }
}


