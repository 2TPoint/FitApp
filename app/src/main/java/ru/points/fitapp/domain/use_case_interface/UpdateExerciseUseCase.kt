package ru.points.fitapp.domain.use_case_interface

import ru.points.fitapp.data.entity.Exercise

interface UpdateExerciseUseCase {
    fun handle(id: Long, title: String,
               description: String?,
               weight: Float?,
               upNextTime: Boolean,
               type: Exercise.Type)
}