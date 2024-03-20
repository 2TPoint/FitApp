package ru.points.fitapp.domain.use_case_interface

import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.ui.exercises.exercise_list.component.PopupEvents

interface InsertExerciseUseCase {
    fun handle(title: String,
               description: String?,
               weight: Float?,
               upNextTime: Boolean,
               type: Exercise.Type)
}