package ru.points.fitapp.domain.use_case_impl

import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.repository.irepository.ExerciseRepository
import ru.points.fitapp.domain.use_case_interface.InsertExerciseUseCase

class InsertExerciseUseCaseImpl(
    private val exerciseRepository: ExerciseRepository
) : InsertExerciseUseCase {
    override fun handle(
        title: String,
        description: String?,
        weight: Float?,
        upNextTime: Boolean,
        type: Exercise.Type
    ) {
        exerciseRepository.insertExercise(
            exercise = Exercise(
                title = title,
                description = description,
                weight = weight,
                upNextTime = upNextTime,
                type = type)
        )
    }
}