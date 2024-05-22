package ru.points.fitapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.points.fitapp.data.converter.Converters

@Entity(
    tableName = "training"
)
@TypeConverters(Converters::class)
data class Training(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "description")
    val description: String = "",
    @ColumnInfo(name = "time")
    val time: String = "",
    @ColumnInfo(name = "exercises_list")
    val exercisesList: MutableList<Exercise> = mutableListOf()
)
