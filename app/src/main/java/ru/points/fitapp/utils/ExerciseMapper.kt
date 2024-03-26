package ru.points.fitapp.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.ScubaDiving
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.vo.ExerciseVo

object ExerciseMapper {
    fun mapToVo(value: Exercise): ExerciseVo {
        return ExerciseVo(
            id = value.id,
            title = value.title,
            subtitle = if (value.value == null) "" else "Вес ${value.value}кг",
            color = mapTypeToColor(value.discriminator),
            icon = mapTypeToIcon(value.discriminator)
        )
    }

    fun mapTypeToColor(item: Exercise.Type): Color {
        return when (item) {
            Exercise.Type.CARDIO -> Color.Green
            Exercise.Type.STRENGTH -> Color.Red
        }
    }

    fun mapTypeToIcon(item: Exercise.Type): ImageVector {
        return when (item) {
            Exercise.Type.CARDIO -> Icons.Default.DirectionsRun
            Exercise.Type.STRENGTH -> Icons.Default.ScubaDiving
        }
    }
}