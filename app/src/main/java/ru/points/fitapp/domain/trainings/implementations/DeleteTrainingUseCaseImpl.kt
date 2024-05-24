package ru.points.fitapp.domain.trainings.implementations

import ru.points.fitapp.data.repository.repository.TrainingsRepository
import ru.points.fitapp.domain.trainings.interfaces.DeleteTrainingUseCase

class DeleteTrainingUseCaseImpl(
    private val trainingsRepository: TrainingsRepository
): DeleteTrainingUseCase {
    override suspend fun handle(id: Long) {
        trainingsRepository.deleteTraining(id = id)
    }
}