package ru.points.fitapp.domain.trainings.interfaces

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.vo.TrainingVo

interface GetTrainingByIdUseCase {
    fun handle(id: Long): Flow<TrainingVo>
}