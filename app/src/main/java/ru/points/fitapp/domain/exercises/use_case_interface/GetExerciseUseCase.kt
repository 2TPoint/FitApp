package ru.points.fitapp.domain.exercises.use_case_interface

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.entity.Exercise

/**
 * @file GetExerciseUseCase.kt
 * @brief Интерфейс use case для получения упражнения по его ID.
 *
 * Этот интерфейс определяет контракт для получения упражнения из репозитория по его уникальному идентификатору.
 * Он использует корутины для асинхронной обработки данных.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
interface GetExerciseUseCase {
    /**
     * Получает упражнение из репозитория по его ID.
     *
     * Использует корутины для асинхронной обработки данных.
     * Возвращает Flow с упражнением, соответствующим переданному ID.
     *
     * @param id Уникальный идентификатор упражнения.
     * @return Flow с упражнением.
     */
    suspend fun handle(id: Long): Flow<Exercise>
}