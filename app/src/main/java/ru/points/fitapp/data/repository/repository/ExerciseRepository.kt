package ru.points.fitapp.data.repository.repository

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.entity.Exercise

interface ExerciseRepository {
    fun insertExercise(exercise: Exercise)
    fun getExercises(): Flow<List<Exercise>>
    fun getExerciseById(id: Long): Flow<Exercise>
    fun updateExercise(exercise: Exercise)
    fun deleteExercise(exercise: Exercise)
}