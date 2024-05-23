package ru.points.fitapp.domain.trainings.interfaces

import ru.points.fitapp.data.vo.TrainingVo

interface ReorderTrainingExercisesUseCase {
    suspend fun handle(training: TrainingVo, from: Int, to: Int)
}