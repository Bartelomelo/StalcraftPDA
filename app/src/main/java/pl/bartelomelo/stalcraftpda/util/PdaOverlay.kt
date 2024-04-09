package pl.bartelomelo.stalcraftpda.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pl.bartelomelo.stalcraftpda.R

@Composable
fun PdaOverlay() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.plastic_phone),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize(),
            contentDescription = "phone case"
        )
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(Color.Yellow)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(Color.Black)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .weight(1f)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.exbo_logo),
                            modifier = Modifier
                                .width(100.dp)
                                .height(50.dp)
                                .padding(top = 5.dp, start = 5.dp),
                            contentDescription = "exbo logo"
                        )
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.front_camera),
                            contentDescription = "front camera"
                        )
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .weight(1f)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Row(modifier = Modifier.padding(bottom = 10.dp, end = 4.dp)) {
                                    Text(text = "")
                                    Image(
                                        painter = painterResource(R.drawable.ic_launcher_background),
                                        contentDescription = "",
                                        colorFilter = ColorFilter.tint(Color.Green),
                                        modifier = Modifier
                                            .size(6.dp)
                                            .clip(CircleShape)
                                    )
                                }
                                Image(
                                    painter = painterResource(id = R.drawable.radio_signal),
                                    modifier = Modifier.size(15.dp),
                                    contentDescription = "radio signal"
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .align(Alignment.CenterVertically)
                                    .weight(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(modifier = Modifier.padding(bottom = 10.dp)) {
                                    Text(text = "")
                                    Image(
                                        painter = painterResource(R.drawable.ic_launcher_background),
                                        contentDescription = "",
                                        colorFilter = ColorFilter.tint(Color.Green),
                                        modifier = Modifier
                                            .size(6.dp)
                                            .clip(CircleShape)
                                    )
                                }
                                Image(
                                    painter = painterResource(id = R.drawable.wifi_signal),
                                    modifier = Modifier.size(15.dp),
                                    contentDescription = "wifi signal"
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .align(Alignment.CenterVertically)
                                    .weight(1f),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Row(modifier = Modifier.padding(start = 5.dp, bottom = 10.dp)) {
                                    Text(text = "")
                                    Image(
                                        painter = painterResource(R.drawable.ic_launcher_background),
                                        contentDescription = "",
                                        colorFilter = ColorFilter.tint(Color.Green),
                                        modifier = Modifier
                                            .size(6.dp)
                                            .clip(CircleShape)
                                    )
                                }
                                Image(
                                    painter = painterResource(id = R.drawable.battery),
                                    modifier = Modifier.size(15.dp),
                                    contentDescription = "battery condition"
                                )
                            }
                        }
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(start = 5.dp, bottom = 5.dp, end = 5.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = CardDefaults.cardColors(Color.White)
                ) {
                    Text(text = "BARTELOMELON")
                }

            }
        }
    }
}

