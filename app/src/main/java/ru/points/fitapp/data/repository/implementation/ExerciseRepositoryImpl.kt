package ru.points.fitapp.data.repository.implementation

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.datasource.dao.ExerciseDao
import ru.points.fitapp.data.datasource.database.FitAppDatabase
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.repository.repository.ExerciseRepository

class ExerciseRepositoryImpl(
    private val dao: ExerciseDao
): ExerciseRepository {
    override fun getExercises(): Flow<List<Exercise>> {
        return dao.readAll()
    }

    override fun insertExercise(exercise: Exercise) {
        dao.create(exercise)
    }

    override fun getExerciseById(id: Long): Flow<Exercise> {
        return dao.readById(id = id)
    }

    override fun updateExercise(exercise: Exercise) {
        dao.update(exercise)
    }

    override fun deleteExercise(exercise: Exercise) {
        dao.delete(exercise)
    }
}