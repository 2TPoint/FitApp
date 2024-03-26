package ru.points.fitapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.points.fitapp.ui.exercises.main.component.ExerciseListViewModel
import ru.points.fitapp.ui.settings.component.SettingsViewModel

val viewModelModule = module {
    viewModel<ExerciseListViewModel> {
        ExerciseListViewModel(
            getExercisesUseCase = get(),
            getExerciseUseCase = get(),
            insertExerciseUseCase = get(),
            updateExerciseUseCase = get()
        )
    }

    viewModel<SettingsViewModel> {
        SettingsViewModel()
    }
}