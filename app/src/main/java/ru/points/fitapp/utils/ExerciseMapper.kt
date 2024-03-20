package ru.points.fitapp.utils

import androidx.compose.ui.graphics.Color
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.vo.ExerciseVo

object ExerciseMapper {
    fun mapToVo(value: Exercise): ExerciseVo {
        return ExerciseVo(
            id = value.id,
            title = value.title,
            description = value.description ?: "",
            value = "${(value.weight ?: 0f)} ${mapTypeToStr(value.type)}" ,
            upNextTime = value.upNextTime ?: false,
            color = mapTypeToColor(value.type),
        )
    }

    fun mapTypeToStr(item: Exercise.Type): String {
        return when (item) {
            Exercise.Type.CARDIO -> "мин"
            Exercise.Type.STRENGTH -> "кг"
        }
    }

    fun mapTypeToColor(item: Exercise.Type): Color {
        return when (item) {
            Exercise.Type.CARDIO -> Color.Green
            Exercise.Type.STRENGTH -> Color.Red
        }
    }
}