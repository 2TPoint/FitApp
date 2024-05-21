package ru.points.fitapp.domain.trainings.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.points.fitapp.data.repository.repository.TrainingsRepository
import ru.points.fitapp.data.vo.TrainingVo
import ru.points.fitapp.domain.trainings.interfaces.GetTrainingByIdUseCase
import ru.points.fitapp.utils.TrainingsMapper.toVo

class GetTrainingByIdUseCaseImpl(
    private val trainingsRepository: TrainingsRepository
): GetTrainingByIdUseCase {
    override fun handle(id: Long): Flow<TrainingVo> = trainingsRepository.getTrainingById(id = id).map { training -> training.toVo() }
}