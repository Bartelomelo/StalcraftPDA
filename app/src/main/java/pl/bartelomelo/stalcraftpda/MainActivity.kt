package pl.bartelomelo.stalcraftpda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.bartelomelo.stalcraftpda.screens.itemlist.ItemListScreen
import pl.bartelomelo.stalcraftpda.ui.theme.StalcraftPDATheme
import pl.bartelomelo.stalcraftpda.util.PdaOverlay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StalcraftPDATheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "item_list_screen") {
                    composable("item_list_screen") {
                        PdaOverlay { ItemListScreen(navController) }
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun StalcraftButton(){
//    StalcraftPDATheme {
//        val rightCorner = with(LocalDensity.current) { 35.dp.toPx() }
//        val rightIndentation = with(LocalDensity.current) { 285.dp.toPx() }
//        val secondRightIndentation = with(LocalDensity.current) { 270.dp.toPx() }
//
//        Box(modifier = Modifier
//            .size(width = 300.dp, height = 50.dp)
//            .drawBehind {
//                val path = Path().apply {
//                    moveTo(0f, 0f)
//                    lineTo(size.width, 0f)
//                    lineTo(size.width, rightCorner)
//                    lineTo(rightIndentation, rightCorner)
//                    lineTo(secondRightIndentation, size.height)
//                    lineTo(0f, size.height)
//
//                    close()
//                }
//                drawPath(
//                    path = path, color = Color.DarkGray
//                )
//            }) {
//            Text(text = "SIEMA")
//        }
//    }
//}
