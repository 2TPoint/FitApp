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

/**
 * @file UseCaseModule.kt
 * @brief Модуль для управления use case'ами упражнений.
 *
 * Этот модуль предоставляет реализации для различных use case'ов, связанных с упражнениями.
 * Он использует экземпляр репозитория упражнений, полученный из модуля репозитория.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
val useCaseModule = module {
    /**
     * Фабрика для создания экземпляра GetExercisesUseCase.
     *
     * Создает экземпляр GetExercisesUseCaseImpl, используя экземпляр репозитория упражнений.
     *
     * @return Экземпляр GetExercisesUseCaseImpl.
     */
    factory<GetExercisesUseCase> {
        GetExercisesUseCaseImpl(
            exerciseRepository = get()
        )
    }

    /**
     * Фабрика для создания экземпляра InsertExerciseUseCase.
     *
     * Создает экземпляр InsertExerciseUseCaseImpl, используя экземпляр репозитория упражнений.
     *
     * @return Экземпляр InsertExerciseUseCaseImpl.
     */
    factory<InsertExerciseUseCase> {
        InsertExerciseUseCaseImpl(
            exerciseRepository = get()
        )
    }

    /**
     * Фабрика для создания экземпляра GetExerciseUseCase.
     *
     * Создает экземпляр GetExerciseUseCaseImpl, используя экземпляр репозитория упражнений.
     *
     * @return Экземпляр GetExerciseUseCaseImpl.
     */
    factory<GetExerciseUseCase> {
        GetExerciseUseCaseImpl(
            exerciseRepository = get()
        )
    }

    /**
     * Фабрика для создания экземпляра UpdateExerciseUseCase.
     *
     * Создает экземпляр UpdateExerciseUseCaseImpl, используя экземпляр репозитория упражнений.
     *
     * @return Экземпляр UpdateExerciseUseCaseImpl.
     */
    factory<UpdateExerciseUseCase> {
        UpdateExerciseUseCaseImpl(
            exerciseRepository = get()
        )
    }
}