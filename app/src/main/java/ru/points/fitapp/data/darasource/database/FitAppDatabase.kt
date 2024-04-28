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

/**
 * @file FitAppDatabase.kt
 * @brief База данных приложения FitApp, использующая Room для работы с SQLite.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 * @version 1.0
 */
@Database(
    version = 1,
    entities = [Exercise::class],
    exportSchema = true
)
abstract class FitAppDatabase : RoomDatabase() {

    /**
     * Получает DAO для работы с упражнениями.
     *
     * @return DAO для упражнений.
     */
    abstract fun getExerciseDao(): ExerciseDao

    companion object {
        @Volatile
        private var INSTANCE: FitAppDatabase? = null

        /**
         * Получает экземпляр базы данных.
         *
         * @param context Контекст приложения.
         * @return Экземпляр базы данных FitApp.
         */
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