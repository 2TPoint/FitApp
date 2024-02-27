package ru.points.fitapp.data.repository.implementation

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.datasource.database.FitAppDatabase
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.repository.irepository.ExerciseRepository

class ExerciseRepositoryImpl(
    private val database: FitAppDatabase
): ExerciseRepository {
    override fun getExercises(): Flow<List<Exercise>> {
        return database.getExerciseDao().getExercises()
    }

    override fun insertExercise(exercise: Exercise) {
        database.getExerciseDao().insertExercise(exercise)
    }

    override fun getExercise(id: Long): Flow<Exercise> {
        return database.getExerciseDao().getExercise(id = id)
    }

    override fun updateExercise(id: Long, title: String, weight: Float?) {
        database.getExerciseDao().updateExercise(
            id = id,
            title = title,
            weight = weight
        )
    }
}