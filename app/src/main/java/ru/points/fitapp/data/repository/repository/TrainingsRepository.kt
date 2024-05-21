package ru.points.fitapp.data.repository.repository

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.entity.Training

interface TrainingsRepository {
    fun getTrainingsList(): Flow<List<Training>>

    fun getTrainingById(id: Long): Flow<Training>

    fun addTraining(name: String, description: String)
}