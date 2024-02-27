package ru.points.fitapp.domain.use_case_interface

import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.vo.ExerciseVo

interface GetExerciseUseCase {
    fun handle(id: Long): Flow<Exercise>
}