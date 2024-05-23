package ru.points.fitapp.domain.trainings.implementations

import ru.points.fitapp.data.repository.repository.TrainingsRepository
import ru.points.fitapp.domain.trainings.interfaces.AddTraining

class AddTrainingImpl(
    private val trainingsRepository: TrainingsRepository
): AddTraining {
    override suspend fun handle(name: String, description: String) {
        trainingsRepository.addTraining(name = name, description = description)
    }
}