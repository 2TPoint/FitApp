package ru.points.fitapp.data.darasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.entity.Training

@Dao
interface TrainingsDao {
    @Query(value = "select * from training")
    fun getAllTrainings(): Flow<List<Training>>

    @Query(value = "select * from training where id = :id")
    fun getTrainingById(id: Long): Flow<Training>

    @Insert
    fun insertTraining(training: Training)

    @Update
    fun updateTraining(training: Training)
}