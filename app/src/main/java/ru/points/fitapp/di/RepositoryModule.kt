package ru.points.fitapp.di

import org.koin.dsl.module
import ru.points.fitapp.data.repository.implementation.ExerciseRepositoryImpl
import ru.points.fitapp.data.repository.implementation.TrainingsRepositoryImpl
import ru.points.fitapp.data.repository.irepository.ExerciseRepository
import ru.points.fitapp.data.repository.repository.TrainingsRepository
import ru.points.fitapp.data.retrofit.FoodRepositoryInterface
import ru.points.fitapp.data.retrofit.repository.FoodRepository

/**
 * @file RepositoryModule.kt
 * @brief Модуль для управления репозиторием упражнений.
 *
 * Этот модуль предоставляет реализацию ExerciseRepository через ExerciseRepositoryImpl.
 * Он использует экземпляр базы данных, полученный из модуля базы данных.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
val repositoryModule = module {
    /**
     * Singleton для ExerciseRepository.
     *
     * Создает экземпляр ExerciseRepositoryImpl, используя экземпляр базы данных.
     * Этот экземпляр базы данных получается из модуля базы данных.
     *
     * @return Экземпляр ExerciseRepositoryImpl.
     */
    single<ExerciseRepository> {
        ExerciseRepositoryImpl(
            database = get()
        )
    }
    single <FoodRepositoryInterface>{
        FoodRepository()
    }
    single<TrainingsRepository> {
        TrainingsRepositoryImpl(
            database = get()
        )
    }
}