package ru.points.fitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EggAlt
import androidx.compose.material.icons.filled.ScubaDiving
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.points.fitapp.navigation.Destinations
import ru.points.fitapp.navigation.FitAppNavHost
import ru.points.fitapp.ui.theme.FitAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitAppTheme {
                val navHostController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            AppCashBottomNavigationBar(
                                navController = navHostController, screens =
                                listOf(
                                    Screen(
                                        icon = Icons.Default.ScubaDiving,
                                        title = "Упражнения",
                                        route = Destinations.EXERCISE_LIST_PAGE
                                    ),
                                    Screen(
                                        icon = Icons.Default.EggAlt,
                                        title = "Еда",
                                        route = Destinations.FOOD_SUMMARY_PAGE
                                    ),
                                    Screen(
                                        icon = Icons.Default.Settings,
                                        title = "Настройки",
                                        route = Destinations.SETTINGS_PAGE
                                    )
                                )
                            )
                        },
                    ) { paddingValues ->
                        FitAppNavHost(controller = navHostController, paddingValues = paddingValues)
                    }
                }
            }
        }
    }
}

@Composable
private fun AppCashBottomNavigationBar(
    navController: NavController,
    screens: List<Screen>,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        screens.forEach { screenItem ->
            BottomNavigationItem(
                selected = screenItem.route == currentDestination?.route,
                onClick = { navController.navigate(screenItem.route) },
                icon = {
                    Icon(
                        screenItem.icon,
                        contentDescription = null
                    )
                },
                label = { Text(text = screenItem.title) },
                alwaysShowLabel = false
            )
        }
    }
}

private data class Screen(
    val icon: ImageVector,
    val title: String,
    val route: String
)