package ru.points.fitapp.domain.trainings.interfaces

interface DeleteTrainingUseCase {
    suspend fun handle(id: Long)
}