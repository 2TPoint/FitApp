package ru.points.fitapp.ui.exercises.exercise_list.component

data class PopupState(
    val selectedId: Long? = null,
    val name: String = "",
    val isWeightUsed: Boolean = false,
    val weight: String = ""
)
