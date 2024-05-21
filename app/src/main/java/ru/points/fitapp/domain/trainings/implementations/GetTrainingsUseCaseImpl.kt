package ru.points.fitapp.domain.trainings.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.points.fitapp.data.repository.repository.TrainingsRepository
import ru.points.fitapp.data.vo.TrainingVo
import ru.points.fitapp.domain.trainings.interfaces.GetTrainingsUseCase
import ru.points.fitapp.utils.TrainingsMapper.toVo

class GetTrainingsUseCaseImpl(
    private val trainingsRepository: TrainingsRepository
): GetTrainingsUseCase {
    override fun handle(): Flow<List<TrainingVo>> = trainingsRepository.getTrainingsList().map { list ->
        list.map { it.toVo() }
    }
}