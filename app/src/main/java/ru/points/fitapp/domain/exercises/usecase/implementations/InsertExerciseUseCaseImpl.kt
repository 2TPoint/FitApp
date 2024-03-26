package ru.points.fitapp.domain.exercises.usecase.implementations

import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.repository.repository.ExerciseRepository
import ru.points.fitapp.domain.exercises.usecase.interfaces.InsertExerciseUseCase
import java.sql.Time
import java.time.LocalDate
import java.time.LocalTime

class InsertExerciseUseCaseImpl(
    private val exerciseRepository: ExerciseRepository
) : InsertExerciseUseCase {
    override fun handle(
        name: String,
        description: String?,
        value: Double,
        discriminator: Exercise.Type,
        time: Time?
    ) {
        exerciseRepository.insertExercise(
            exercise = Exercise(
                title = name,
                description = description,
                value = value,
                discriminator = discriminator,
                time = time
            )
        )
    }
}