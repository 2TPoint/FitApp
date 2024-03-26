package ru.points.fitapp.di

import org.koin.dsl.module
import ru.points.fitapp.data.repository.implementation.ExerciseRepositoryImpl
import ru.points.fitapp.data.repository.repository.ExerciseRepository

val repositoryModule = module {
    single<ExerciseRepository> {
        ExerciseRepositoryImpl(
            dao = get()
        )
    }
}