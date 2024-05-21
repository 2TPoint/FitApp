package ru.points.fitapp.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.points.fitapp.data.converter.Converter
import ru.points.fitapp.data.darasource.dao.FoodDao
import ru.points.fitapp.data.datasource.dao.ExerciseDao
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.entity.Food

@Database(
    version = 1,
    entities = [Exercise::class, Food::class]
)
@TypeConverters(value = [Converter::class])
abstract class FitAppDatabase : RoomDatabase() {

    abstract fun getExerciseDao(): ExerciseDao

    abstract fun getFoodDao(): FoodDao
}