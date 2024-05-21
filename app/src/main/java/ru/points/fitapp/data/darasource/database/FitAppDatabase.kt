package ru.points.fitapp.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.points.fitapp.data.converter.Converters
import ru.points.fitapp.data.darasource.dao.FoodDao
import ru.points.fitapp.data.darasource.dao.TrainingsDao
import ru.points.fitapp.data.datasource.dao.ExerciseDao
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.entity.Food
import ru.points.fitapp.data.entity.Training

@Database(
    version = 5,
    entities = [Exercise::class, Food::class, Training::class]
)
@TypeConverters(Converters::class)
abstract class FitAppDatabase : RoomDatabase() {

    abstract fun getExerciseDao(): ExerciseDao

    abstract fun getFoodDao(): FoodDao

    abstract fun getTrainingsDao(): TrainingsDao

}