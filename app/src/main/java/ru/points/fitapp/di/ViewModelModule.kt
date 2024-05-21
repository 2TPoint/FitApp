package ru.points.fitapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.points.fitapp.ui.exercises.exercise_list.component.ExerciseListViewModel
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
            updateExerciseUseCase = get()
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
        SettingsViewModel()
    }
}