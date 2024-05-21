package ru.points.fitapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.points.fitapp.di.databaseModule
import ru.points.fitapp.di.repositoryModule
import ru.points.fitapp.di.useCaseModule
import ru.points.fitapp.di.viewModelModule

/**
 * @file FitAppApplication.kt
 * @brief Класс приложения FitApp.
 *
 * Инициализирует Koin для внедрения зависимостей в приложении FitApp, включая модули базы данных, репозиториев, use case и ViewModel.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
class FitAppApplication : Application() {

    /**
     * @brief Инициализирует Koin.
     *
     * Вызывается при создании приложения. Инициализирует Koin с настройками логирования и контекстом приложения, а также загружает модули зависимостей.
     */
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@FitAppApplication)
            modules(
                databaseModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}