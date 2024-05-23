package ru.points.fitapp.domain.trainings.implementations

import ru.points.fitapp.data.repository.repository.TrainingsRepository
import ru.points.fitapp.data.vo.TrainingVo
import ru.points.fitapp.domain.trainings.interfaces.ReorderTrainingExercisesUseCase

class ReorderTrainingExercisesUseCaseImpl(
    private val trainingsRepository: TrainingsRepository
) : ReorderTrainingExercisesUseCase {
    override suspend fun handle(training: TrainingVo, from: Int, to: Int) {

    }
}