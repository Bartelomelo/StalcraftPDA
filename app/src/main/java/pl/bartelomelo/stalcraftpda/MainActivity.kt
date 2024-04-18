package pl.bartelomelo.stalcraftpda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import pl.bartelomelo.stalcraftpda.screens.itemcategoryscreen.ItemCategoryScreen
import pl.bartelomelo.stalcraftpda.screens.itemlist.ItemListScreen
import pl.bartelomelo.stalcraftpda.screens.itemsubcategoryscreen.ItemSubcategoryScreen
import pl.bartelomelo.stalcraftpda.ui.theme.StalcraftPDATheme
import pl.bartelomelo.stalcraftpda.util.PdaOverlay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface (
                color = Color.DarkGray
            ) {
                StalcraftPDATheme {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "item_list_screen") {
                        composable("item_list_screen") {
                            PdaOverlay { ItemListScreen(navController) }
                        }
                        composable(
                            "item_category_screen/global/{item}/{category}",
                            arguments = listOf(
                                navArgument("item") {
                                    type = NavType.StringType
                                },
                                navArgument("category") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val route = it.arguments?.getString("category")
                            PdaOverlay {
                                ItemCategoryScreen(navController = navController, route = route!!)
                            }
                        }
                        composable(
                            "item_subcategory_screen/global/{item}/{category}/{subcategory}",
                            arguments = listOf(
                                navArgument("item") {
                                    type = NavType.StringType
                                },
                                navArgument("category") {
                                    type = NavType.StringType
                                },
                                navArgument("subcategory") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val route = listOf(
                                it.arguments?.getString("category")!!,
                                it.arguments?.getString("subcategory")!!
                            )
                            PdaOverlay {
                                ItemSubcategoryScreen(navController = navController, route = route)
                            }
                        }
                        composable("item_detail_screen") {
                            PdaOverlay {
                                Text(text = "Item detail screen!")
                            }
                        }
                    }
                }
            }
        }
    }
}
