package ru.points.fitapp.di

import org.koin.dsl.module
import ru.points.fitapp.domain.use_case_impl.GetExerciseUseCaseImpl
import ru.points.fitapp.domain.use_case_interface.GetExercisesUseCase

val useCaseModule = module {
    factory<GetExercisesUseCase> {
        GetExerciseUseCaseImpl(
            exerciseRepository = get()
        )
    }
}