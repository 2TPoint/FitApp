package ru.points.fitapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.points.fitapp.ui.exercises.main.component.ExerciseListViewModel
import ru.points.fitapp.ui.food.component.FoodSummaryViewModel
import ru.points.fitapp.ui.foodSearch.component.FoodSearchViewModel
import ru.points.fitapp.ui.settings.component.SettingsViewModel

val viewModelModule = module {
    viewModel<ExerciseListViewModel> {
        ExerciseListViewModel(
            getExercisesUseCase = get(),
            getExerciseByIdUseCase = get(),
            insertExerciseUseCase = get(),
            updateExerciseUseCase = get()
        )
    }

    viewModel<SettingsViewModel> {
        SettingsViewModel()
    }

    viewModel<FoodSummaryViewModel> {
        FoodSummaryViewModel(
            foodDao = get()
        )
    }

    viewModel<FoodSearchViewModel> {
        FoodSearchViewModel(
            getNutritionsForCommonListUseCase = get()
        )
    }
}