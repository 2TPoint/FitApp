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
    fun insertExercise(exercise: Exercise)

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
    fun updateExercise(id: Long, title: String, description: String?,
                       weight: Float?,
                       upNextTime: Boolean,
                       type: Exercise.Type)

    /**
     * Получает упражнение по его идентификатору.
     *
     * @param id Идентификатор упражнения.
     * @return Flow с упражнением.
     */
    fun getExercise(id: Long): Flow<Exercise>
}
