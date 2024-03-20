package ru.points.fitapp.data.datasource.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.points.fitapp.data.datasource.dao.ExerciseDao
import ru.points.fitapp.data.entity.Exercise

@Database(
    version = 1,
    entities = [Exercise::class],
    exportSchema = true
)
abstract class FitAppDatabase : RoomDatabase() {
    abstract fun getExerciseDao(): ExerciseDao

    companion object {
        @Volatile
        private var INSTANCE: FitAppDatabase? = null

        fun getDatabase(context: Context): FitAppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FitAppDatabase::class.java,
                    "fitapp_database"
                )
                    .fallbackToDestructiveMigration() // Adding destructive migration
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}