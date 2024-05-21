package ru.points.fitapp.domain.exercises.usecase.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.repository.repository.ExerciseRepository
import ru.points.fitapp.data.vo.ExerciseVo
import ru.points.fitapp.domain.exercises.usecase.interfaces.GetExercisesUseCase
import ru.points.fitapp.utils.ExerciseMapper

class GetExercisesUseCaseImpl(
    private val exerciseRepository: ExerciseRepository
) : GetExercisesUseCase {
    override fun handle(): Flow<List<Exercise>> {
        return exerciseRepository.getExercises()
    }
}