package ru.points.fitapp.ui.main.exercises.component

import java.sql.Time

data class ExercisePopupState(
    val selectedId: Long? = null,
    val name: String = "",
    val description: String? = null,
    val upNextTime: Boolean = false,
    val value: String = "",
    val isWeight: Boolean = true,
    val time: Time? = null
)
