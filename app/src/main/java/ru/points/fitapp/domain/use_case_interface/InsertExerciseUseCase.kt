package ru.points.fitapp.domain.use_case_interface

interface InsertExerciseUseCase {
    fun handle(title: String, weight: Float?)
}