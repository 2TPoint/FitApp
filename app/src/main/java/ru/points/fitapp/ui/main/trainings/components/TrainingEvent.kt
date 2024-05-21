package ru.points.fitapp.ui.main.trainings.components

import ru.points.fitapp.utils.Event

sealed class TrainingEvent: Event {

    data class UpdatePopupShowedState(
        val id: Long? = null,
        val isShowed: Boolean
    ) : Event

}

sealed class TrainingPopUpEvents: Event {

    data class SaveChanges(val name: String, val description: String) : TrainingPopUpEvents()

}