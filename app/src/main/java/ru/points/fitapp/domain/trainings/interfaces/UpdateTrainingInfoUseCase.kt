package ru.points.fitapp.domain.trainings.interfaces

interface UpdateTrainingInfoUseCase {
    suspend fun handle(id:Long, name:String, description: String)
}