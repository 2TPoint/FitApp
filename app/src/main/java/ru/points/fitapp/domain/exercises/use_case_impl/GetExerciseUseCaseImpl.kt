package ru.points.fitapp.domain.exercises.use_case_impl

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.repository.irepository.ExerciseRepository
import ru.points.fitapp.domain.exercises.use_case_interface.GetExerciseUseCase

/**
 * @file GetExerciseUseCaseImpl.kt
 * @brief Реализация use case для получения упражнения по его ID.
 *
 * Этот класс предоставляет реализацию для получения упражнения из репозитория по его уникальному идентификатору.
 * Он использует корутины для асинхронной обработки данных.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
class GetExerciseUseCaseImpl(
    private val exerciseRepository: ExerciseRepository
): GetExerciseUseCase {
    /**
     * Получает упражнение из репозитория по его ID.
     *
     * Использует корутины для асинхронной обработки данных.
     * Возвращает Flow с упражнением, соответствующим переданному ID.
     *
     * @param id Уникальный идентификатор упражнения.
     * @return Flow с упражнением.
     */
    override fun handle(id: Long): Flow<Exercise> {
        return exerciseRepository.getExercise(id = id)
    }
}