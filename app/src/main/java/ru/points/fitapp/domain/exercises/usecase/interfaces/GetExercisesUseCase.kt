package ru.points.fitapp.domain.exercises.usecase.interfaces

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.vo.ExerciseVo

interface GetExercisesUseCase {
    fun handle(): Flow<List<Exercise>>
}