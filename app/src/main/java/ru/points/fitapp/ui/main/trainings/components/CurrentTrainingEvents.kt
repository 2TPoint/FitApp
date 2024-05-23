package ru.points.fitapp.ui.main.trainings.components

import ru.points.fitapp.data.vo.TrainingVo
import ru.points.fitapp.utils.Event

sealed class CurrentTrainingEvents: Event {

    data class AddNewExercise(val training: TrainingVo, val id: Long): Event

    data class DeleteSelectedExercise(val pos: Int): Event
    data class ReorderExercises(val training: TrainingVo, val fromIndex: Int, val toIndex: Int): Event

}