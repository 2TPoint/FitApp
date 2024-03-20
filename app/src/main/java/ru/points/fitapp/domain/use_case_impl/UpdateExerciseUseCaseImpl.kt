package ru.points.fitapp.domain.use_case_impl

import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.repository.irepository.ExerciseRepository
import ru.points.fitapp.domain.use_case_interface.UpdateExerciseUseCase

class UpdateExerciseUseCaseImpl(
    private val exerciseRepository: ExerciseRepository
): UpdateExerciseUseCase {
    override fun handle(id: Long, title: String,
                        description: String?,
                        weight: Float?,
                        upNextTime: Boolean,
                        type: Exercise.Type) {
        exerciseRepository.updateExercise(
            id = id,
            title = title,
            description = description,
            weight = weight,
            upNextTime = upNextTime,
            type = type
        )
    }
}