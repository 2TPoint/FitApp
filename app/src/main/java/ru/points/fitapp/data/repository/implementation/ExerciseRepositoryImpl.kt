package ru.points.fitapp.data.repository.implementation

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.datasource.database.FitAppDatabase
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.repository.irepository.ExerciseRepository


/**
 * @file ExerciseRepositoryImpl.kt
 * @brief Реализация репозитория для работы с упражнениями в приложении FitApp.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 * @version 1.0
 */
class ExerciseRepositoryImpl(
    private val database: FitAppDatabase
): ExerciseRepository {

    override fun getExercises(): Flow<List<Exercise>> {
        return database.getExerciseDao().getExercises()
    }

    override suspend fun insertExercise(exercise: Exercise) {
        database.getExerciseDao().insertExercise(exercise)
    }

    override suspend fun getExercise(id: Long): Flow<Exercise> {
        return database.getExerciseDao().getExercise(id)
    }

    override suspend fun deleteExercise(id: Long) {
        database.getExerciseDao().deleteExercise(id)
    }

    override suspend fun updateExercise(
        exercise: Exercise) {
        database.getExerciseDao().updateExercise(
            exercise = exercise
        )
    }
}
