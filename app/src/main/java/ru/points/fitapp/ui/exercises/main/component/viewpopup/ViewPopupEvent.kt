package ru.points.fitapp.ui.exercises.main.component.viewpopup

import ru.points.fitapp.utils.Event

sealed class ViewPopupEvent: Event {
    data object ChangeVisibility: ViewPopupEvent()
    data class InputValue(val value: String): ViewPopupEvent()
    data object UpNextTime: ViewPopupEvent()
    data class InputTime(val value: String): ViewPopupEvent()
    data object Save: ViewPopupEvent()
}