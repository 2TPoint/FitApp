package ru.points.fitapp.domain.exercises.usecase.interfaces

import ru.points.fitapp.data.entity.Exercise

interface UpdateExerciseUseCase {
    fun handle(exercise: Exercise)
}