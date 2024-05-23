package ru.points.fitapp.domain.exercises.use_case_interface

interface DeleteExerciseUseCase {
    suspend fun handle(id: Long)
}