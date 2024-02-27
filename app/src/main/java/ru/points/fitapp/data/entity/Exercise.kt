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
    @ColumnInfo(name = "weight", )
    val weight: Float? = null,
    @ColumnInfo(name = "upNextTime")
    val upNextTime: Boolean = false,
    @ColumnInfo(name = "type")
    val type: Type = Type.CARDIO
) {
    enum class Type {
        STRENGTH,
        CARDIO
    }
}