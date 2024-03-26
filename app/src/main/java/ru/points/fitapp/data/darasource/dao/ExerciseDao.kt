package ru.points.fitapp.data.datasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.entity.Exercise

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(exercise: Exercise)

    @Query("SELECT * FROM exercise")
    fun readAll(): Flow<List<Exercise>>

    @Query("SELECT * FROM exercise WHERE id = :id ")
    fun readById(id: Long): Flow<Exercise>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(exercise: Exercise)

    @Delete
    fun delete(exercise: Exercise)
}