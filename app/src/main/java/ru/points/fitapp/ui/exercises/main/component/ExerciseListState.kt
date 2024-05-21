package ru.points.fitapp.ui.exercises.main.component

import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.ui.exercises.main.component.addpopup.AddPopupState
import ru.points.fitapp.ui.exercises.main.component.viewpopup.ViewPopupState

data class ExerciseListState(
    val list: List<Exercise> = listOf(),
    val viewPopupState: ViewPopupState = ViewPopupState(),
    val addPopupState: AddPopupState = AddPopupState()
)