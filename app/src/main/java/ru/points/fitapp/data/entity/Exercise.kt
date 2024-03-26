package ru.points.fitapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.time.LocalDate
import java.time.LocalTime

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
    val value: Double,
    @ColumnInfo(name = "upNextTime")
    val upNextTime: Boolean = false,
    @ColumnInfo(name = "discriminator")
    val discriminator: Type,
    @ColumnInfo("time")
    val time: Time?
) {
    enum class Type {
        STRENGTH,
        CARDIO
    }
}