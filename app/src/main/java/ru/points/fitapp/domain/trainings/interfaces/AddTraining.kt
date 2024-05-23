package ru.points.fitapp.domain.trainings.interfaces

interface AddTraining {
    suspend fun handle(name: String, description: String)
}