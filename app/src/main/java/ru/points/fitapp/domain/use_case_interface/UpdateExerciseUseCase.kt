package ru.points.fitapp.domain.use_case_interface

interface UpdateExerciseUseCase {
    fun handle(id: Long, title: String, weight: Float?)
}