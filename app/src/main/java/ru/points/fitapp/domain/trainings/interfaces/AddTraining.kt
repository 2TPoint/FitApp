package ru.points.fitapp.domain.trainings.interfaces

interface AddTraining {
    fun handle(name: String, description: String)
}