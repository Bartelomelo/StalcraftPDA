package pl.bartelomelo.stalcraftpda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.bartelomelo.stalcraftpda.ui.theme.StalcraftPDATheme
import pl.bartelomelo.stalcraftpda.util.PdaOverlay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StalcraftPDATheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home_screen") {
                    composable("home_screen") {
                        PdaOverlay()
                    }
                }
            }
        }
    }
}
