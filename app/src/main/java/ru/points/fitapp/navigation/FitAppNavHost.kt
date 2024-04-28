package ru.points.fitapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.koin.androidx.compose.getViewModel
import ru.points.fitapp.ui.exercises.exercise_list.component.ExerciseListViewModel
import ru.points.fitapp.ui.exercises.exercise_list.screen.ExercisesScreenController
import ru.points.fitapp.ui.settings.component.SettingsViewModel
import ru.points.fitapp.ui.settings.screen.SettingsScreenController

/**
 * @file FitAppNavHost.kt
 * @brief Composable функция для настройки навигации в приложении.
 *
 * Эта функция использует NavHost для настройки навигации между различными экранами приложения.
 * Она определяет маршруты для списка упражнений и настроек, используя константы из объекта Destinations.
 *
 * @param controller NavHostController для управления навигацией.
 * @param paddingValues PaddingValues для добавления отступов вокруг NavHost.
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
@Composable
fun FitAppNavHost(
    controller: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = controller,
        startDestination = Destinations.EXERCISE_LIST_PAGE,
        modifier = Modifier.padding(paddingValues)
        ) {
        composable(
            route = Destinations.EXERCISE_LIST_PAGE,
        ) {
            ExercisesScreenController(
                modifier = Modifier.fillMaxSize(),
                viewModel = getViewModel<ExerciseListViewModel>()
            )
        }

        composable(
            route = Destinations.SETTINGS_PAGE,
        ) {
            SettingsScreenController(
                modifier = Modifier.fillMaxSize(),
                viewModel = getViewModel<SettingsViewModel>()
            )
        }
    }
}