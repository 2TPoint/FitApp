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
    @Query("SELECT * FROM exercise")
    fun getExercises(): Flow<List<Exercise>>

    @Insert
    suspend fun insertExercise(exercise: Exercise)

    @Update
    suspend fun updateExercise(exercise: Exercise)

    @Query("SELECT * FROM exercise WHERE id = :id")
    fun getExercise(id: Long): Flow<Exercise>

    @Query("DELETE FROM exercise WHERE id = :id")
    suspend fun deleteExercise(id: Long)
}