package ru.points.fitapp.navigation

import CurrentTrainingScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import ru.points.fitapp.ui.calculator.CalculatorScreen
import ru.points.fitapp.ui.food.component.FoodSummaryViewModel
import ru.points.fitapp.ui.food.screen.FoodSummaryScreenController
import ru.points.fitapp.ui.foodSearch.component.FoodSearchViewModel
import ru.points.fitapp.ui.foodSearch.screen.FoodSearchScreenController
import ru.points.fitapp.ui.main.exercises.component.ExerciseListViewModel
import ru.points.fitapp.ui.main.screen.MainScreenController
import ru.points.fitapp.ui.main.trainings.components.CurrentTrainingViewModel
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
            MainScreenController(
                modifier = Modifier.fillMaxSize(),
                exercisesViewModel = getViewModel<ExerciseListViewModel>(),
                navController = controller
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

        composable(
            route = Destinations.FOOD_SUMMARY_PAGE,
        ) {
            FoodSummaryScreenController(
                navigateTo = controller::navigate,
                modifier = Modifier.fillMaxSize(),
                viewModel = getViewModel<FoodSummaryViewModel>()
            )
        }

        composable(
            route = Destinations.FOOD_SEARCH_PAGE,
        ) {
            FoodSearchScreenController(
                modifier = Modifier.fillMaxSize(),
                viewModel = getViewModel<FoodSearchViewModel>()
            )
        }

        composable(
            route = Destinations.CALCULATION_PAGE,
        ) {
            CalculatorScreen()
        }

        composable(
            route = "${Destinations.CURRENT_TRAINING_PAGE}/{${TRAINING_ARGUMENT_ID_KEY}}",
            arguments = listOf(
                navArgument(name = TRAINING_ARGUMENT_ID_KEY) {
                    type = NavType.LongType
                }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(TRAINING_ARGUMENT_ID_KEY)
            id?.let {
                val viewModel: CurrentTrainingViewModel =
                    getViewModel(parameters = { parametersOf(it) })
                CurrentTrainingScreen(viewModel = viewModel)
            }
        }
    }
}