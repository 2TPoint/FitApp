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
            value = "${(value.value)} ${mapTypeToStr(value.isWeight)}" ,
            upNextTime = value.upNextTime ?: false,
            color = mapTypeToColor(value.isWeight),
            time = value.time
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
    fun mapTypeToStr(weightUsed: Boolean): String {
        return when (weightUsed) {
            false -> "мин"
            true -> "кг"
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
    fun mapTypeToColor(weightUsed: Boolean): Color {
        return when (weightUsed) {
            true -> Color.Red
            false -> Color.Green
        }
    }

    fun colorToType(color: Color): Boolean{
        return when(color) {
            Color.Red -> true
            else -> false
        }
    }

    fun ExerciseVo.toExercise(): Exercise{
        return Exercise(
            id = this.id,
            title = this.title,
            description = this.description,
            value = this.value.substring(0, this.value.indexOf(" ")).toDouble(),
            upNextTime = this.upNextTime,
            time = this.time,
            isWeight = colorToType(this.color)
        )
    }
}