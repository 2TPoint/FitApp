package ru.points.fitapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercise"
)
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "weight")
    val weight: Float,
    @ColumnInfo(name = "upNextTime")
    val upNextTime: Boolean,
    @ColumnInfo(name = "type")
    val type: Type
) {
    enum class Type {
        STRENGTH,
        CARDIO
    }
}