package ru.points.fitapp.domain.use_case_impl

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.repository.irepository.ExerciseRepository
import ru.points.fitapp.domain.use_case_interface.GetExerciseUseCase

class GetExerciseUseCaseImpl(
    private val exerciseRepository: ExerciseRepository
): GetExerciseUseCase {
    override fun handle(id: Long): Flow<Exercise> {
        return exerciseRepository.getExercise(id = id)
    }
}