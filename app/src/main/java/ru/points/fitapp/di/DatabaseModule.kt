package ru.points.fitapp.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.points.fitapp.data.datasource.database.FitAppDatabase

val databaseModule = module {
    factory {
        Room.databaseBuilder(
            context = androidContext(),
            klass = FitAppDatabase::class.java,
            name = "FitAppDatabase.db"
        ).build()
    }
}