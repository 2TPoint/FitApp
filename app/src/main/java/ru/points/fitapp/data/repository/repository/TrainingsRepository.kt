package ru.points.fitapp.data.repository.repository

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.entity.Training

interface TrainingsRepository {
    fun getTrainingsList(): Flow<List<Training>>

    fun getTrainingById(id: Long): Flow<Training?>

    suspend fun addTraining(name: String, description: String)

    suspend fun addExerciseToTraining(training: Training, id: Long)

    suspend fun deleteExerciseFromTraining(training: Training, pos: Long)

    suspend fun deleteExerciseFromAllTrainings(id: Long)

    suspend fun updateTraining(id: Long, name: String, description: String)

    suspend fun deleteTraining(id: Long)
}