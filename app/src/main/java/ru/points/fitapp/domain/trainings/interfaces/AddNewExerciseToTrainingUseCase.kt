package ru.points.fitapp.domain.trainings.interfaces

import ru.points.fitapp.data.vo.ExerciseVo
import ru.points.fitapp.data.vo.TrainingVo

interface AddNewExerciseToTrainingUseCase {
    fun handle(training: TrainingVo, exercise: ExerciseVo)
}