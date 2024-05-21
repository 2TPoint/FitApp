package ru.points.fitapp.domain.trainings.interfaces

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.vo.TrainingVo

interface GetTrainingsUseCase {

    fun handle(): Flow<List<TrainingVo>>
}