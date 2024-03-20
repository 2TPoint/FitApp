package ru.points.fitapp.data.vo

import androidx.compose.ui.graphics.Color

data class ExerciseVo(
    val id: Long,
    val title: String,
    val description: String,
    val value: String,
    val upNextTime: Boolean,
    val color: Color
)