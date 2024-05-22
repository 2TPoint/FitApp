package ru.points.fitapp.domain.trainings.implementations

import ru.points.fitapp.data.repository.repository.TrainingsRepository
import ru.points.fitapp.data.vo.ExerciseVo
import ru.points.fitapp.data.vo.TrainingVo
import ru.points.fitapp.domain.trainings.interfaces.AddNewExerciseToTrainingUseCase
import ru.points.fitapp.utils.ExerciseMapper.toExercise
import ru.points.fitapp.utils.TrainingsMapper.toTraining

class AddNewExerciseToTrainingImplUseCase(
    private val trainingsRepository: TrainingsRepository
): AddNewExerciseToTrainingUseCase {
    override fun handle(training: TrainingVo, exercise: ExerciseVo) {
        trainingsRepository.addExerciseToTraining(training = training.toTraining(), exercise = exercise.toExercise())
    }
}