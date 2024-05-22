package ru.points.fitapp.data.repository.repository

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.entity.Training
import ru.points.fitapp.data.vo.ExerciseVo
import ru.points.fitapp.data.vo.TrainingVo

interface TrainingsRepository {
    fun getTrainingsList(): Flow<List<Training>>

    fun getTrainingById(id: Long): Flow<Training>

    fun addTraining(name: String, description: String)

    fun addExerciseToTraining(training: Training, exercise: Exercise)
}