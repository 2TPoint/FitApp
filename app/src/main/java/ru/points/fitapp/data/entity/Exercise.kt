package ru.points.fitapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

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
    @ColumnInfo(name = "value")
    val value: Double = 0.0,
    @ColumnInfo(name = "upNextTime")
    val upNextTime: Boolean? = false,
    @ColumnInfo(name = "time")
    val time: Time? = null,
    @ColumnInfo(name = "isWeight")
    val isWeight: Boolean = true
)