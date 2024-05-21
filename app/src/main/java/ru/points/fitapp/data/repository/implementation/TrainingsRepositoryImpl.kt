package ru.points.fitapp.data.repository.implementation

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.datasource.database.FitAppDatabase
import ru.points.fitapp.data.entity.Training
import ru.points.fitapp.data.repository.repository.TrainingsRepository

class TrainingsRepositoryImpl(
    private val database: FitAppDatabase
): TrainingsRepository {
    override fun getTrainingsList(): Flow<List<Training>> = database.getTrainingsDao().getAllTrainings()
    override fun getTrainingById(id: Long): Flow<Training> = database.getTrainingsDao().getTrainingById(id = id)
    override fun addTraining(name: String, description: String) = database.getTrainingsDao().insertTraining(Training(
        name = name,
        description = description
    ))
}