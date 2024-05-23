package ru.points.fitapp.domain.exercises.use_case_impl

import ru.points.fitapp.data.repository.irepository.ExerciseRepository
import ru.points.fitapp.data.repository.repository.TrainingsRepository
import ru.points.fitapp.domain.exercises.use_case_interface.DeleteExerciseUseCase

class DeleteExerciseUseCaseImpl(
    val exerciseRepository: ExerciseRepository,
    val trainingRepository: TrainingsRepository
): DeleteExerciseUseCase {
    override suspend fun handle(id: Long) {
        trainingRepository.deleteExerciseFromAllTrainings(id)
        exerciseRepository.deleteExercise(id)
    }
}