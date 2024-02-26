package ru.points.fitapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.points.fitapp.ui.exercises.exercise_list.component.ExerciseListViewModel

val viewModelModule = module {
    viewModel<ExerciseListViewModel> {
        ExerciseListViewModel(
            useCase = get()
        )
    }
}