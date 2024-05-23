package ru.points.fitapp.domain.trainings.interfaces

import ru.points.fitapp.data.vo.TrainingVo


interface DeleteSelectedExerciseUseCase {
    suspend fun handle(training: TrainingVo, pos: Long)
}