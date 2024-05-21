package ru.points.fitapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Food(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "calories")
    val calories: Long,
    @ColumnInfo(name = "fat")
    val fats: Long,
    @ColumnInfo(name = "carbohydrates")
    val carbohydrates: Long,
    @ColumnInfo(name = "protein")
    val proteins: Long
)
