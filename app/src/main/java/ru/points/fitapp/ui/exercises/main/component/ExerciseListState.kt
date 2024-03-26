package ru.points.fitapp.ui.exercises.main.component

import ru.points.fitapp.data.vo.ExerciseVo

data class ExerciseListState(
    val list: List<ExerciseVo> = listOf(),
    val isPopupShowed: Boolean = false,
    val popupState: PopupState = PopupState()
)