package ru.points.fitapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.points.fitapp.di.databaseModule

class FitAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@FitAppApplication)
            modules(
                databaseModule
            )
        }
    }
}