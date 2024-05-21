package ru.points.fitapp.domain.exercises.usecase.implementations

import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.repository.repository.ExerciseRepository
import ru.points.fitapp.domain.exercises.usecase.interfaces.UpdateExerciseUseCase

class UpdateExerciseUseCaseImpl(
    private val exerciseRepository: ExerciseRepository
): UpdateExerciseUseCase {
    override fun handle(exercise: Exercise) {
        exerciseRepository.updateExercise(
            exercise
        )
    }
}