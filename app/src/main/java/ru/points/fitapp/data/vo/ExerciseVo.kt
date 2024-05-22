package ru.points.fitapp.data.vo

import androidx.compose.ui.graphics.Color
import java.sql.Time

/**
 * @file ExerciseVo.kt
 * @brief Класс, представляющий упражнение в виде объекта для передачи данных (Value Object) в приложении FitApp.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 * @version 1.0
 */
data class ExerciseVo(
    val id: Long,
    val title: String,
    val description: String,
    val value: String,
    val upNextTime: Boolean,
    val time: Time?,
    val color: Color
)
