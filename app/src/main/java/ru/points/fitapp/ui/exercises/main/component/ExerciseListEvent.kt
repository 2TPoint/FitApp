package ru.points.fitapp.ui.exercises.main.component

import ru.points.fitapp.utils.Event

sealed class ExerciseListEvent: Event {
    data class UpdatePopupShowedState(
        val id: Long? = null,
        val isShowed: Boolean
    ): ExerciseListEvent()
}

sealed class PopupEvents: Event {
    data class UpdateToggle(
        val value: Boolean
    ): PopupEvents()

    data class UpdateName(
        val value: String
    ): PopupEvents()

    data class UpdateWeight(
        val value: String
    ): PopupEvents()

    data object SaveExercise: PopupEvents()
}
