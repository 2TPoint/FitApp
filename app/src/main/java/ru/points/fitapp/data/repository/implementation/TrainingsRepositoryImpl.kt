package ru.points.fitapp.data.repository.implementation

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.datasource.database.FitAppDatabase
import ru.points.fitapp.data.entity.Training
import ru.points.fitapp.data.repository.repository.TrainingsRepository

class TrainingsRepositoryImpl(
    private val database: FitAppDatabase
): TrainingsRepository {
    override fun getTrainingsList(): Flow<List<Training>> = database.getTrainingsDao().getAllTrainings()
    override fun getTrainingById(id: Long): Flow<Training> {
        return database.getTrainingsDao().getTrainingById(id = id)
    }
    override suspend fun addTraining(name: String, description: String) {
        database.getTrainingsDao().insertTraining(Training(
            name = name,
            description = description
        ))
    }

    override suspend fun addExerciseToTraining(training: Training, id: Long) {
        training.exercisesList.add(id)
        database.getTrainingsDao().updateTraining(training)
    }

    override suspend fun deleteExerciseFromTraining(training: Training, pos: Long) {
        training.exercisesList.removeAt(pos.toInt())
        database.getTrainingsDao().updateTraining(training = training)
    }

    override suspend fun deleteExerciseFromAllTrainings(id: Long) {
        database.getTrainingsDao().removeExerciseFromAllTrainings(id)
    }
}