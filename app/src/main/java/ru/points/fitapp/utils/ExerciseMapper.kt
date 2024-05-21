package ru.points.fitapp.utils

import androidx.compose.ui.graphics.Color
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.vo.ExerciseVo

/**
 * @file ExerciseMapper.kt
 * @brief Преобразователь упражнений.
 *
 * Предоставляет функции для преобразования упражнений из одной формы в другую, включая преобразование типа упражнения в строку и цвет.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
object ExerciseMapper {
    /**
     * @brief Преобразует упражнение в VO.
     *
     * Преобразует объект упражнения в объект VO, включая преобразование типа упражнения в строку и цвет.
     *
     * @param value Упражнение для преобразования.
     * @return Преобразованный объект VO.
     */
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

    /**
     * @brief Преобразует тип упражнения в строку.
     *
     * Преобразует тип упражнения в строку, представляющую единицы измерения.
     *
     * @param item Тип упражнения.
     * @return Строка, представляющая единицы измерения.
     */
    fun mapTypeToStr(item: Exercise.Type): String {
        return when (item) {
            Exercise.Type.CARDIO -> "мин"
            Exercise.Type.STRENGTH -> "кг"
        }
    }

    /**
     * @brief Преобразует тип упражнения в цвет.
     *
     * Преобразует тип упражнения в цвет, используемый для отображения в пользовательском интерфейсе.
     *
     * @param item Тип упражнения.
     * @return Цвет, используемый для отображения типа упражнения.
     */
    fun mapTypeToColor(item: Exercise.Type): Color {
        return when (item) {
            Exercise.Type.CARDIO -> Color.Green
            Exercise.Type.STRENGTH -> Color.Red
        }
    }
}