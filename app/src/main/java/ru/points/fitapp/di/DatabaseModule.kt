package ru.points.fitapp.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.points.fitapp.data.darasource.dao.FoodDao
import ru.points.fitapp.data.darasource.dao.TrainingsDao
import ru.points.fitapp.data.datasource.dao.ExerciseDao
import ru.points.fitapp.data.datasource.database.FitAppDatabase
import ru.points.fitapp.data.manager.PreferencesManager

/**
 * @file DatabaseModule.kt
 * @brief Модуль для настройки и управления базой данных.
 *
 * Этот модуль отвечает за настройку базы данных Room для приложения FitApp.
 * Он предоставляет фабрику для создания экземпляра FitAppDatabase.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
val databaseModule = module {
    /**
     * Фабрика для создания экземпляра FitAppDatabase.
     *
     * Этот метод фабрики использует databaseBuilder от Room для создания экземпляра базы данных.
     * База данных названа "FitAppDatabase.db" и ассоциирована с классом FitAppDatabase.
     *
     * @return Экземпляр базы данных Room.
     */
    factory {
        Room.databaseBuilder(
            context = androidContext(),
            klass = FitAppDatabase::class.java,
            name = "FitAppDatabase.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    factory<ExerciseDao> {
        get<FitAppDatabase>().getExerciseDao()
    }

    factory<FoodDao> {
        get<FitAppDatabase>().getFoodDao()
    }

    factory<TrainingsDao> {
        get<FitAppDatabase>().getTrainingsDao()
    }

    single {
        PreferencesManager(androidContext())
    }
}