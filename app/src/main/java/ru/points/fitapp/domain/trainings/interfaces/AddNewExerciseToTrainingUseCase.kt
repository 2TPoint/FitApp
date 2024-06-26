package ru.points.fitapp.domain.trainings.interfaces

import ru.points.fitapp.data.vo.TrainingVo

interface AddNewExerciseToTrainingUseCase {
    suspend fun handle(training: TrainingVo, id: Long)
}