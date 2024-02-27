package ru.points.fitapp.ui.settings.component

import ru.points.fitapp.utils.Event

sealed class SettingsEvents : Event {
    data class UpdateBooleanEvent(
        val value: Boolean,
        val type: ToggleType
    ): SettingsEvents() {
        enum class ToggleType {
            THEME,
            WEIGHT
        }
    }
}