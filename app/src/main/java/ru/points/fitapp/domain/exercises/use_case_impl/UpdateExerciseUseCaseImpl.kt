package ru.points.fitapp.domain.exercises.use_case_impl

import ru.points.fitapp.data.repository.irepository.ExerciseRepository
import ru.points.fitapp.data.vo.ExerciseVo
import ru.points.fitapp.domain.exercises.use_case_interface.UpdateExerciseUseCase
import ru.points.fitapp.utils.ExerciseMapper.toExercise

class UpdateExerciseUseCaseImpl(
    private val exerciseRepository: ExerciseRepository
): UpdateExerciseUseCase {
    override suspend fun handle(exercise: ExerciseVo) {
        exerciseRepository.updateExercise(exercise = exercise.toExercise())
    }
}