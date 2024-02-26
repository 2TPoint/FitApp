package ru.points.fitapp.domain.use_case_interface

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.vo.ExerciseVo

interface GetExercisesUseCase {
    fun handle(): Flow<List<ExerciseVo>>
}