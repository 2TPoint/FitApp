package ru.points.fitapp.data.darasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import ru.points.fitapp.data.entity.Training

@Dao
interface TrainingsDao {
    @Query("SELECT * FROM training")
    fun getAllTrainings(): Flow<List<Training>>

    @Query("SELECT * FROM training WHERE id = :id")
    fun getTrainingById(id: Long): Flow<Training>

    @Insert
    suspend fun insertTraining(training: Training)

    @Update
    suspend fun updateTraining(training: Training)

    @Transaction
    suspend fun removeExerciseFromAllTrainings(exerciseId: Long) {
        val trainings = getAllTrainings().first()
        trainings.forEach { training ->
            if (training.exercisesList.contains(exerciseId)) {
                training.exercisesList.remove(exerciseId)
                updateTraining(training)
            }
        }
    }

    @Delete
    suspend fun deleteTraining(training: Training)
}
