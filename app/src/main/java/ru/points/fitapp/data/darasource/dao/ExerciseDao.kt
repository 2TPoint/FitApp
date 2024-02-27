package ru.points.fitapp.data.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.entity.Exercise

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise")
    fun getExercises(): Flow<List<Exercise>>

    @Insert
    fun insertExercise(exercise: Exercise)

    @Query("UPDATE exercise SET title = :title, weight = :weight WHERE id = :id ")
    fun updateExercise(id: Long, title: String, weight: Float?)

    @Query("SELECT * FROM exercise WHERE id = :id")
    fun getExercise(id: Long): Flow<Exercise>
}