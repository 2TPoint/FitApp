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


/**
 * @file ExerciseRepositoryImpl.kt
 * @brief Реализация репозитория для работы с упражнениями в приложении FitApp.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 * @version 1.0
 */
class ExerciseRepositoryImpl(
    private val database: FitAppDatabase
): ExerciseRepository {

    /**
     * Получает список всех упражнений из базы данных.
     *
     * @return Flow с списком упражнений.
     */
    override fun getExercises(): Flow<List<Exercise>> {
        return database.getExerciseDao().getExercises()
    }

    /**
     * Вставляет новое упражнение в базу данных.
     *
     * @param exercise Упражнение для вставки.
     */
    override fun insertExercise(exercise: Exercise) {
        database.getExerciseDao().insertExercise(exercise)
    }

    /**
     * Получает упражнение по его идентификатору.
     *
     * @param id Идентификатор упражнения.
     * @return Flow с упражнением.
     */
    override fun getExercise(id: Long): Flow<Exercise> {
        return database.getExerciseDao().getExercise(id = id)
    }

    /**
     * Обновляет упражнение в базе данных по его идентификатору.
     *
     * @param id Идентификатор упражнения.
     * @param title Название упражнения.
     * @param description Описание упражнения.
     * @param weight Вес упражнения.
     * @param upNextTime Флаг, указывающий, должно ли упражнение быть выполнено в следующий раз.
     * @param type Тип упражнения.
     */
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
