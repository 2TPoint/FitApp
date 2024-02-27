package ru.points.fitapp.domain.use_case_interface

import ru.points.fitapp.ui.exercises.exercise_list.component.PopupEvents

interface InsertExerciseUseCase {
    fun handle(title: String, weight: Float?)
}