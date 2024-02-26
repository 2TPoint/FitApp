package ru.points.fitapp.data.vo

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class ExerciseVo(
    val id: Long,
    val title: String,
    val subtitle: String,
    val color: Color,
    val icon: ImageVector
)