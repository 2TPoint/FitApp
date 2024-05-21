package ru.points.fitapp.di

import org.koin.dsl.module
import ru.points.fitapp.domain.exercises.use_case_impl.GetExerciseUseCaseImpl
import ru.points.fitapp.domain.exercises.use_case_impl.GetExercisesUseCaseImpl
import ru.points.fitapp.domain.exercises.use_case_impl.InsertExerciseUseCaseImpl
import ru.points.fitapp.domain.exercises.use_case_interface.GetExerciseUseCase
import ru.points.fitapp.domain.exercises.use_case_interface.GetExercisesUseCase
import ru.points.fitapp.domain.exercises.use_case_interface.InsertExerciseUseCase
import ru.points.fitapp.domain.food.GetFoodInstantResponceByQueryUseCase
import ru.points.fitapp.domain.food.GetNutritionsForCommonListUseCase
import ru.points.fitapp.domain.trainings.implementations.AddTrainingImpl
import ru.points.fitapp.domain.trainings.implementations.GetTrainingByIdUseCaseImpl
import ru.points.fitapp.domain.trainings.implementations.GetTrainingsUseCaseImpl
import ru.points.fitapp.domain.trainings.interfaces.AddTraining
import ru.points.fitapp.domain.trainings.interfaces.GetTrainingByIdUseCase
import ru.points.fitapp.domain.trainings.interfaces.GetTrainingsUseCase

val useCaseModule = module {

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

    factory<GetExercisesUseCase> {
        GetExercisesUseCaseImpl(
            exerciseRepository = get()
        )
    }

    factory<GetFoodInstantResponceByQueryUseCase> {
        GetFoodInstantResponceByQueryUseCase(foodRepositoryInterface = get())
    }

    factory<GetNutritionsForCommonListUseCase> {
        GetNutritionsForCommonListUseCase(foodRepository = get(), getFoodInstantResponceByQueryUseCase = get())
    }

    factory<GetTrainingsUseCase> {
        GetTrainingsUseCaseImpl(trainingsRepository = get())
    }

    factory<AddTraining> {
        AddTrainingImpl(trainingsRepository = get())
    }

    factory<GetTrainingByIdUseCase> {
        GetTrainingByIdUseCaseImpl(trainingsRepository = get())
    }
}