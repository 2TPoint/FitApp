package ru.points.fitapp.domain.exercises.usecase.interfaces

import ru.points.fitapp.data.entity.Exercise
import java.sql.Time
import java.time.LocalDate
import java.time.LocalTime

interface InsertExerciseUseCase {
    fun handle(
        name: String,
        description: String?,
        value: Double,
        discriminator: Exercise.Type,
        time: Time?
    )
}