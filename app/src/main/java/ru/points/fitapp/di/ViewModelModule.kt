package ru.points.fitapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.points.fitapp.ui.exercises.exercise_list.component.ExerciseListViewModel
import ru.points.fitapp.ui.settings.component.SettingsViewModel

val viewModelModule = module {
    viewModel<ExerciseListViewModel> {
        ExerciseListViewModel(
            useCase = get()
        )
    }

    viewModel<SettingsViewModel> {
        SettingsViewModel()
    }
}