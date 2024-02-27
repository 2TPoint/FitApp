package ru.points.fitapp.di

import org.koin.dsl.module
import ru.points.fitapp.domain.use_case_impl.GetExerciseUseCaseImpl
import ru.points.fitapp.domain.use_case_impl.GetExercisesUseCaseImpl
import ru.points.fitapp.domain.use_case_impl.InsertExerciseUseCaseImpl
import ru.points.fitapp.domain.use_case_impl.UpdateExerciseUseCaseImpl
import ru.points.fitapp.domain.use_case_interface.GetExerciseUseCase
import ru.points.fitapp.domain.use_case_interface.GetExercisesUseCase
import ru.points.fitapp.domain.use_case_interface.InsertExerciseUseCase
import ru.points.fitapp.domain.use_case_interface.UpdateExerciseUseCase

val useCaseModule = module {
    factory<GetExercisesUseCase> {
        GetExercisesUseCaseImpl(
            exerciseRepository = get()
        )
    }

    factory<InsertExerciseUseCase> {
        InsertExerciseUseCaseImpl(
            exerciseRepository = get()
        )
    }

    factory<GetExerciseUseCase> {
        GetExerciseUseCaseImpl(
            exerciseRepository = get()
        )
    }

    factory<UpdateExerciseUseCase> {
        UpdateExerciseUseCaseImpl(
            exerciseRepository = get()
        )
    }
}