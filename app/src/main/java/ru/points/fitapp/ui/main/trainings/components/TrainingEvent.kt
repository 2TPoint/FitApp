package ru.points.fitapp.ui.main.trainings.components

import ru.points.fitapp.data.vo.TrainingVo
import ru.points.fitapp.utils.Event

sealed class TrainingEvent: Event {

    data class UpdatePopupShowedState(
        val id: Long? = null,
        val isShowed: Boolean
    ) : Event

    data class OpenTraining(
        val training: TrainingVo
    ): Event

}

sealed class TrainingPopUpEvents: Event {

    data class SaveChanges(val name: String, val description: String) : TrainingPopUpEvents()

    data object DeleteTraining : TrainingPopUpEvents()
}

sealed class TrainingExerciseEvent: Event {
    data class DeleteTraining(val id: Long)
}