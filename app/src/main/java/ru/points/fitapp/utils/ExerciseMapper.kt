package ru.points.fitapp.utils

import androidx.compose.ui.graphics.Color
import org.koin.java.KoinJavaComponent.inject
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.manager.PreferencesManager
import ru.points.fitapp.data.vo.ExerciseVo
import java.util.prefs.PreferenceChangeEvent

/**
 * @file ExerciseMapper.kt
 * @brief Преобразователь упражнений.
 *
 * Предоставляет функции для преобразования упражнений из одной формы в другую, включая преобразование типа упражнения в строку и цвет.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */

private val preferencesManager by inject<PreferencesManager>(PreferencesManager::class.java)

object ExerciseMapper {

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

    fun mapTypeToStr(weightUsed: Boolean): String {
        return when (weightUsed) {
            false -> if (preferencesManager.isM) "м" else "мили"
            true -> if (preferencesManager.isKg) "кг" else "фунты"
        }
    }

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