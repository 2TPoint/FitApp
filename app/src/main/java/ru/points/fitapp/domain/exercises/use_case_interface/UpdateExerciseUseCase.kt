package ru.points.fitapp.domain.exercises.use_case_interface

import ru.points.fitapp.data.vo.ExerciseVo

interface UpdateExerciseUseCase {
    suspend fun handle(exercise: ExerciseVo)
}