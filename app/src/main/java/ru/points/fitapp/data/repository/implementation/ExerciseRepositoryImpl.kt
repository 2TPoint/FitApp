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
}