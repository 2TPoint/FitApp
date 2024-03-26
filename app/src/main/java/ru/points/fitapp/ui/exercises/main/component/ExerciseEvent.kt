package ru.points.fitapp.ui.exercises.main.component

import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.utils.Event
import java.time.LocalTime

sealed class ExerciseEvent : Event {
    data class SelectCard(val id: Long): ExerciseEvent()
}
