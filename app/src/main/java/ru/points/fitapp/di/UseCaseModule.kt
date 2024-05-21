package ru.points.fitapp.di

import com.example.surimusakotlin.domain.usecase.search.GetFoodInstantResponceByQueryUseCase
import com.example.surimusakotlin.domain.usecase.search.GetNutritionsForCommonListUseCase
import org.koin.dsl.module
import ru.points.fitapp.domain.exercises.usecase.implementations.GetExerciseByIdUseCaseImpl
import ru.points.fitapp.domain.exercises.usecase.implementations.GetExercisesUseCaseImpl
import ru.points.fitapp.domain.exercises.usecase.implementations.InsertExerciseUseCaseImpl
import ru.points.fitapp.domain.exercises.usecase.implementations.UpdateExerciseUseCaseImpl
import ru.points.fitapp.domain.exercises.usecase.interfaces.GetExerciseByIdUseCase
import ru.points.fitapp.domain.exercises.usecase.interfaces.GetExercisesUseCase
import ru.points.fitapp.domain.exercises.usecase.interfaces.InsertExerciseUseCase
import ru.points.fitapp.domain.exercises.usecase.interfaces.UpdateExerciseUseCase

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

    factory<GetExerciseByIdUseCase> {
        GetExerciseByIdUseCaseImpl(
            exerciseRepository = get()
        )
    }

    factory<UpdateExerciseUseCase> {
        UpdateExerciseUseCaseImpl(
            exerciseRepository = get()
        )
    }

    factory<GetFoodInstantResponceByQueryUseCase> {
        GetFoodInstantResponceByQueryUseCase(foodRepositoryInterface = get())
    }

    factory<GetNutritionsForCommonListUseCase> {
        GetNutritionsForCommonListUseCase(foodRepositoryInterface = get())
    }
}