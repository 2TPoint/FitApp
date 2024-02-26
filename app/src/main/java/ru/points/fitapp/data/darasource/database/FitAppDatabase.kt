package ru.points.fitapp.data.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.points.fitapp.data.datasource.dao.ExerciseDao
import ru.points.fitapp.data.entity.Exercise

@Database(
    version = 1,
    entities = [Exercise::class]
)
abstract class FitAppDatabase: RoomDatabase() {
    abstract fun getExerciseDao(): ExerciseDao
}