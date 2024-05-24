package ru.points.fitapp.domain.trainings.implementations

import ru.points.fitapp.data.repository.repository.TrainingsRepository
import ru.points.fitapp.domain.trainings.interfaces.UpdateTrainingInfoUseCase

class UpdateTrainingInfoUseCaseImpl(
    private val trainingsRepository: TrainingsRepository
): UpdateTrainingInfoUseCase {
    override suspend fun handle(id: Long, name: String, description: String) {
        trainingsRepository.updateTraining(id = id, name = name, description = description)
    }
}