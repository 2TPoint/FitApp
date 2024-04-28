package ru.points.fitapp.data.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.entity.Exercise

/**
 * @file ExerciseDao.kt
 * @brief DAO (Data Access Object) для работы с данными упражнений в базе данных.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 * @version 1.0
 */
@Dao
interface ExerciseDao {

    /**
     * Получает список всех упражнений из базы данных.
     *
     * @return Flow с списком упражнений.
     */
    @Query("SELECT * FROM exercise")
    fun getExercises(): Flow<List<Exercise>>

    /**
     * Вставляет новое упражнение в базу данных.
     *
     * @param exercise Упражнение для вставки.
     */
    @Insert
    fun insertExercise(exercise: Exercise)

    /**
     * Обновляет упражнение в базе данных по его идентификатору.
     *
     * @param id Идентификатор упражнения.
     * @param title Название упражнения.
     * @param description Описание упражнения.
     * @param weight Вес упражнения.
     * @param upNextTime Флаг, указывающий, должно ли упражнение быть усложнено в следующий раз.
     * @param type Тип упражнения.
     */
    @Query("UPDATE exercise SET title = :title, description = :description, weight = :weight, upNextTime = :upNextTime, type = :type WHERE id = :id ")
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
    @Query("SELECT * FROM exercise WHERE id = :id")
    fun getExercise(id: Long): Flow<Exercise>
}