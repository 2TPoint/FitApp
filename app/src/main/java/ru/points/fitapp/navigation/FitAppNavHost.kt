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
import ru.points.fitapp.ui.exercises.main.component.ExerciseListViewModel
import ru.points.fitapp.ui.exercises.main.screen.ExercisesScreenController
import ru.points.fitapp.ui.settings.component.SettingsViewModel
import ru.points.fitapp.ui.settings.screen.SettingsScreenController

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