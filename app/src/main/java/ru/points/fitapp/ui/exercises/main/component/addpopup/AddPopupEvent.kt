package ru.points.fitapp.ui.exercises.main.component.addpopup

import ru.points.fitapp.utils.Event

sealed class AddPopupEvent: Event {
    data object ChangeVisibility: AddPopupEvent()

    data class InputName(val name: String): AddPopupEvent()

    data class InputDescription(val description: String): AddPopupEvent()

    data object SwitchWeightUsing: AddPopupEvent()
    data class InputWeight(val value: String): AddPopupEvent()

    data object SwitchDistanceUsing: AddPopupEvent()
    data class InputDistance(val value: String): AddPopupEvent()

    data object SwitchTimeUsing: AddPopupEvent()
    data class InputTime(val value: String): AddPopupEvent()

    data object Save: AddPopupEvent()
}