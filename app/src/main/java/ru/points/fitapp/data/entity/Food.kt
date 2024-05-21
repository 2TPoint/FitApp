package ru.points.fitapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Food(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "weight")
    val weight: Double,
    @ColumnInfo(name = "calories")
    val calories: Double,
    @ColumnInfo(name = "fat")
    val fats: Double,
    @ColumnInfo(name = "carbohydrates")
    val carbohydrates: Double,
    @ColumnInfo(name = "protein")
    val proteins: Double
)
