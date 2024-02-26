package ru.points.fitapp.ui.exercises.exercise_list.component

import ru.points.fitapp.data.vo.ExerciseVo

data class ExerciseListState(
    val list: List<ExerciseVo> = listOf()
)