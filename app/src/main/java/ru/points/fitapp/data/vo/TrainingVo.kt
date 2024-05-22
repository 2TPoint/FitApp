package ru.points.fitapp.data.vo

data class TrainingVo(
    val id: Int,
    val name: String,
    val description: String,
    val time: String,
    val exercisesList: MutableList<ExerciseVo>
)
