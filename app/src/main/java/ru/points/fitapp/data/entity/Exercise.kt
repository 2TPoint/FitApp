package ru.points.fitapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @file Exercise.kt
 * @brief Класс, представляющий упражнение в приложении FitApp.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 * @version 1.0
 */
@Entity(
    tableName = "exercise"
)
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "weight")
    val weight: Float? = null,
    @ColumnInfo(name = "upNextTime")
    val upNextTime: Boolean? = false,
    @ColumnInfo(name = "type")
    val type: Type = Type.STRENGTH
) {
    /**
     * Перечисление типов упражнений.
     */
    enum class Type {
        STRENGTH,
        CARDIO
    }
}