package ru.points.fitapp.domain.use_case_interface

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.vo.ExerciseVo

/**
 * @file GetExercisesUseCase.kt
 * @brief Интерфейс use case для получения списка упражнений.
 *
 * Этот интерфейс определяет контракт для получения списка упражнений в виде VO.
 * Он использует корутины для асинхронной обработки данных.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
interface GetExercisesUseCase {
    /**
     * Получает список упражнений в виде VO.
     *
     * Использует корутины для асинхронной обработки данных.
     * Возвращает Flow с списком упражнений в виде VO.
     *
     * @return Flow с списком упражнений в виде VO.
     */
    fun handle(): Flow<List<ExerciseVo>>
}