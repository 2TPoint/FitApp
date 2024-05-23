package ru.points.fitapp.data.repository.irepository

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.entity.Exercise

/**
 * @file ExerciseRepository.kt
 * @brief Интерфейс репозитория для работы с упражнениями в приложении FitApp.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 * @version 1.0
 */
interface ExerciseRepository {

    /**
     * Получает список всех упражнений из базы данных.
     *
     * @return Flow с списком упражнений.
     */
    fun getExercises(): Flow<List<Exercise>>

    /**
     * Вставляет новое упражнение в базу данных.
     *
     * @param exercise Упражнение для вставки.
     */
    suspend fun insertExercise(exercise: Exercise)

    /**
     * Обновляет упражнение в базе данных по его идентификатору.
     *
     * @param id Идентификатор упражнения.
     * @param title Название упражнения.
     * @param description Описание упражнения.
     * @param weight Вес упражнения.
     * @param upNextTime Флаг, указывающий, должно ли упражнение быть выполнено в следующий раз.
     * @param type Тип упражнения.
     */
    suspend fun updateExercise(
        exercise: Exercise)

    /**
     * Получает упражнение по его идентификатору.
     *
     * @param id Идентификатор упражнения.
     * @return Flow с упражнением.
     */
    suspend fun getExercise(id: Long): Flow<Exercise>

    suspend fun deleteExercise(id: Long)
}
