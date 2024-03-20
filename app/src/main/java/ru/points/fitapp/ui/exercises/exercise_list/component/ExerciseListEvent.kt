package ru.points.fitapp.ui.exercises.exercise_list.component

import ru.points.fitapp.data.entity.Exercise
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

    data class UpdateDescription(
        val value: String
    ): PopupEvents()

    data class UpdateWeight(
        val value: String
    ): PopupEvents()

    data class UpdateType(
        val value: Exercise.Type
    ): PopupEvents()

    data object SaveExercise: PopupEvents()
}
