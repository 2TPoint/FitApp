package ru.points.fitapp.data.repository.implementation

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.datasource.dao.ExerciseDao
import ru.points.fitapp.data.datasource.database.FitAppDatabase
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.repository.irepository.ExerciseRepository
import kotlin.concurrent.Volatile


class ExerciseRepositoryImpl(
    private val database: FitAppDatabase
): ExerciseRepository {
    override fun getExercises(): Flow<List<Exercise>> {
        return database.getExerciseDao().getExercises()
    }

    override fun insertExercise(exercise: Exercise) {
        database.getExerciseDao().insertExercise(exercise)
    }

    override fun getExercise(id: Long): Flow<Exercise> {
        return database.getExerciseDao().getExercise(id = id)
    }

    override fun updateExercise(id: Long, title: String, description: String?,
                                weight: Float?,
                                upNextTime: Boolean,
                                type: Exercise.Type) {
        database.getExerciseDao().updateExercise(
            id = id,
            title = title,
            description = description,
            weight = weight,
            upNextTime = upNextTime,
            type = type
        )
    }
}