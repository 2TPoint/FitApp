package ru.points.fitapp.data.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.entity.Exercise
import java.sql.Time

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

    @Query("UPDATE exercise SET title = :title, description = :description, value = :value, upNextTime = :upNextTime, isWeight = :isWeight, time = :time WHERE id = :id")
    suspend fun updateExercise(id: Long, title: String, description: String?, value: Double?, upNextTime: Boolean, isWeight: Boolean, time: Time?)

    @Query("SELECT * FROM exercise WHERE id = :id")
    fun getExercise(id: Long): Flow<Exercise>

    @Query("DELETE FROM exercise WHERE id = :id")
    suspend fun deleteExercise(id: Long)
}