package ru.points.fitapp.domain.exercises.usecase.interfaces

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.entity.Exercise

interface GetExerciseByIdUseCase {
    fun handle(id: Long): Flow<Exercise>
}