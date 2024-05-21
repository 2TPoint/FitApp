package ru.points.fitapp.data.vo

import ru.points.fitapp.data.entity.Exercise

data class TrainingVo(
    val id: Int,
    val name: String,
    val description: String,
    val time: String,
    val exercisesList: List<Exercise>
)
