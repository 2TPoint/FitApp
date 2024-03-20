package ru.points.fitapp.ui.exercises.exercise_list.component

import ru.points.fitapp.data.entity.Exercise

data class PopupState(
    val selectedId: Long? = null,
    val name: String = "",
    val description: String? = null,
    val upNextTime: Boolean = false,
    val weight: String = "",
    val type: Exercise.Type = Exercise.Type.STRENGTH
)
