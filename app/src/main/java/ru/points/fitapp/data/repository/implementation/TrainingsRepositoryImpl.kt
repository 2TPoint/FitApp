package ru.points.fitapp.data.repository.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import ru.points.fitapp.data.datasource.database.FitAppDatabase
import ru.points.fitapp.data.entity.Training
import ru.points.fitapp.data.repository.repository.TrainingsRepository

class TrainingsRepositoryImpl(
    private val database: FitAppDatabase
) : TrainingsRepository {
    override fun getTrainingsList(): Flow<List<Training>> =
        database.getTrainingsDao().getAllTrainings()

    override fun getTrainingById(id: Long): Flow<Training?> {
        return try {
            database.getTrainingsDao().getTrainingById(id = id)
        } catch (e: Exception) {
            flow<Training> { null }
        }
    }

    override suspend fun addTraining(name: String, description: String) {
        database.getTrainingsDao().insertTraining(
            Training(
                name = name,
                description = description
            )
        )
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

    override suspend fun updateTraining(id: Long, name: String, description: String) {
        val training = database.getTrainingsDao().getTrainingById(id).firstOrNull()
        if (training != null) {
            training.name = name
            training.description = description
            database.getTrainingsDao().updateTraining(training)
        }
    }

    override suspend fun deleteTraining(id: Long) {
        val training = database.getTrainingsDao().getTrainingById(id).firstOrNull()
        if (training != null)
            database.getTrainingsDao().deleteTraining(training = training)
    }
}