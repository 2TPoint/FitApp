package ru.points.fitapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.points.fitapp.ui.food.component.FoodSummaryViewModel
import ru.points.fitapp.ui.foodSearch.component.FoodSearchViewModel
import ru.points.fitapp.ui.main.exercises.component.ExerciseListViewModel
import ru.points.fitapp.ui.main.trainings.components.CurrentTrainingViewModel
import ru.points.fitapp.ui.main.trainings.components.TrainingsViewModel
import ru.points.fitapp.ui.settings.component.SettingsViewModel

/**
 * @file ViewModelModule.kt
 * @brief Модуль для управления ViewModel'ами упражнений.
 *
 * Этот модуль предоставляет реализации для ViewModel'ов, связанных с упражнениями и настройками.
 * Он использует экземпляры use case'ов, полученные из модуля use case'ов.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
val viewModelModule = module {
    /**
     * ViewModel для списка упражнений.
     *
     * Создает экземпляр ExerciseListViewModel, используя экземпляры use case'ов.
     *
     * @return Экземпляр ExerciseListViewModel.
     */
    viewModel<ExerciseListViewModel> {
        ExerciseListViewModel(
            getExercisesUseCase = get(),
            getExerciseUseCase = get(),
            insertExerciseUseCase = get(),
            get()
        )
    }

    /**
     * ViewModel для настроек приложения.
     *
     * Создает экземпляр SettingsViewModel без параметров.
     *
     * @return Экземпляр SettingsViewModel.
     */
    viewModel<SettingsViewModel> {
        SettingsViewModel(preferencesManager = get())
    }

    viewModel<FoodSummaryViewModel> {
        FoodSummaryViewModel(
            foodDao = get()
        )
    }

    viewModel<FoodSearchViewModel> {
        FoodSearchViewModel(
            getNutritionsForCommonListUseCase = get(),
            foodDao = get()
        )
    }

    viewModel<TrainingsViewModel> {
        TrainingsViewModel(
            addTrainingsUseCase = get(),
            getTrainingsUseCase = get(),
            getTrainingByIdUseCase = get()
        )
    }

    viewModel<CurrentTrainingViewModel> { (currentId: Long) ->
        CurrentTrainingViewModel(
            addNewExerciseToTrainingUseCase = get(),
            getTrainingById = get(),
            currentId = currentId,
            getExercisesUseCase = get(),
            reorderTrainingExercisesUseCase = get(),
            deleteSelectedExerciseUseCase = get()
        )
    }


}