package ru.points.fitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EggAlt
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.EggAlt
import androidx.compose.material.icons.outlined.ElectricBolt
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.koin.android.ext.android.inject
import ru.points.fitapp.data.manager.PreferencesManager
import ru.points.fitapp.navigation.Destinations
import ru.points.fitapp.navigation.FitAppNavHost
import ru.points.fitapp.ui.theme.FitAppTheme

/**
 * @file MainActivity.kt
 * @brief Главная активность приложения FitApp.
 *
 * Обеспечивает основной пользовательский интерфейс приложения, включая навигацию и отображение экранов.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
class MainActivity : ComponentActivity() {

    val preferences by inject<PreferencesManager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitAppTheme(darkTheme = preferences.isDark) {
                val navHostController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            AppCashBottomNavigationBar(
                                navController = navHostController,
                                screens = baseRoutes()
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

/**
 * @file MainActivity.kt
 * @brief Компонент нижней навигационной панели.
 *
 * Отображает нижнюю навигационную панель с элементами навигации.
 *
 * @param navController Контроллер навигации для перехода между экранами.
 * @param screens Список экранов для отображения в нижней навигационной панели.
 * @param modifier Модификатор для настройки внешнего вида компонента.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
@Composable
private fun AppCashBottomNavigationBar(
    navController: NavController,
    screens: List<Screen>,
    modifier: Modifier = Modifier
) {
    BottomNavigation(modifier = modifier, backgroundColor = MaterialTheme.colorScheme.primary) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        screens.forEach { screenItem ->
            BottomNavigationItem(
                selected = screenItem.route == currentDestination?.route,
                onClick = {
                    navController.navigate(screenItem.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    if (screenItem.route == currentDestination?.route) {
                        Icon(
                            screenItem.filledIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(30.dp)
                        )
                    } else {
                        Icon(
                            screenItem.outlinedIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                label = {
                    Text(
                        text = screenItem.title,
                        modifier = Modifier.padding(top = 25.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 12.sp
                    )
                },
                alwaysShowLabel = false,
                modifier = Modifier.padding(vertical = 6.dp)
            )
        }
    }
}

/**
 * @file MainActivity.kt
 * @brief Данные экрана.
 *
 * Содержит данные для отображения элемента навигационной панели.
 *
 * @param filledIcon Закрашенная иконка элемента.
 * @param outlinedIcon Пустая иконка элемента.
 * @param title Заголовок элемента.
 * @param route Маршрут для перехода к экрану.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
private data class Screen(
    val title: String,
    val route: String,
    val filledIcon: ImageVector,
    val outlinedIcon: ImageVector
)


@Composable
private fun baseRoutes() = listOf(
    Screen(
        title = stringResource(id = R.string.exercises),
        route = Destinations.EXERCISE_LIST_PAGE,
        filledIcon = Icons.Filled.ElectricBolt,
        outlinedIcon = Icons.Outlined.ElectricBolt,
    ),
    Screen(
        title = stringResource(id = R.string.eat),
        route = Destinations.FOOD_SUMMARY_PAGE,
        filledIcon = Icons.Filled.EggAlt,
        outlinedIcon = Icons.Outlined.EggAlt
    ),
    Screen(
        title = stringResource(R.string.settings),
        route = Destinations.SETTINGS_PAGE,
        filledIcon = Icons.Filled.Settings,
        outlinedIcon = Icons.Outlined.Settings
    )
)