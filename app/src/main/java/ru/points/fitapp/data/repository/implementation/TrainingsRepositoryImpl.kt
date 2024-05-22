package ru.points.fitapp.data.repository.implementation

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.datasource.database.FitAppDatabase
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.entity.Training
import ru.points.fitapp.data.repository.repository.TrainingsRepository
import ru.points.fitapp.data.vo.ExerciseVo
import ru.points.fitapp.data.vo.TrainingVo

class TrainingsRepositoryImpl(
    private val database: FitAppDatabase
): TrainingsRepository {
    override fun getTrainingsList(): Flow<List<Training>> = database.getTrainingsDao().getAllTrainings()
    override fun getTrainingById(id: Long): Flow<Training> = database.getTrainingsDao().getTrainingById(id = id)
    override fun addTraining(name: String, description: String) = database.getTrainingsDao().insertTraining(Training(
        name = name,
        description = description
    ))

    override fun addExerciseToTraining(training: Training, exercise: Exercise) {
        training.exercisesList.add(exercise)
        database.getTrainingsDao().updateTraining(training)
    }
}