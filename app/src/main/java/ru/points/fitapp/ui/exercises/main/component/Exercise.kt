package ru.points.fitapp.ui.exercises.main.component

data class Exercise(
    val id: Long,
    val name: String,
    val description: String?,
    val value: Double,
    val time: Int?,
    val improveValue: Boolean = false
)
