package ru.points.fitapp.ui.main.trainings.components.states

import ru.points.fitapp.data.vo.ExerciseVo
import ru.points.fitapp.data.vo.TrainingVo

data class CurrentTrainingState(
    val training: TrainingVo = TrainingVo(id = 0,
        name = "",
        description = "",
        time = "",
        exercisesList = mutableListOf()
    ),
    val allExercises: List<ExerciseVo> = listOf()
)