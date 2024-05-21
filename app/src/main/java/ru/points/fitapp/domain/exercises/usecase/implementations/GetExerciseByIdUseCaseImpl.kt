package ru.points.fitapp.domain.exercises.usecase.implementations

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.repository.repository.ExerciseRepository
import ru.points.fitapp.domain.exercises.usecase.interfaces.GetExerciseByIdUseCase

class GetExerciseByIdUseCaseImpl(
    private val exerciseRepository: ExerciseRepository
): GetExerciseByIdUseCase {
    override fun handle(id: Long): Flow<Exercise> {
        return exerciseRepository.getExerciseById(id = id)
    }
}