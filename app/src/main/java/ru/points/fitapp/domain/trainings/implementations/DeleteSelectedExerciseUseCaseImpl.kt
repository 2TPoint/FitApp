package ru.points.fitapp.domain.trainings.implementations

import ru.points.fitapp.data.repository.repository.TrainingsRepository
import ru.points.fitapp.data.vo.TrainingVo
import ru.points.fitapp.domain.trainings.interfaces.DeleteSelectedExerciseUseCase
import ru.points.fitapp.utils.TrainingsMapper.toTraining

class DeleteSelectedExerciseUseCaseImpl(
    private val trainingsRepository: TrainingsRepository
): DeleteSelectedExerciseUseCase {
    override suspend fun handle(training: TrainingVo, pos: Long) {
        trainingsRepository.deleteExerciseFromTraining(training.toTraining(), pos)
    }

}