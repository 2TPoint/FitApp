package ru.points.fitapp.data.repository.irepository

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.entity.Exercise

interface ExerciseRepository {
    fun getExercises(): Flow<List<Exercise>>

    fun insertExercise(exercise: Exercise)

    fun updateExercise(id: Long, title: String, weight: Float?)

    fun getExercise(id: Long): Flow<Exercise>
}