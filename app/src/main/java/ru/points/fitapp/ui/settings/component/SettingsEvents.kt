package ru.points.fitapp.ui.settings.component

import ru.points.fitapp.utils.Event

/**
 * @file SettingsEvents.kt
 * @brief Sealed класс для событий настроек.
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
sealed class SettingsEvents : Event {
    /**
     * @brief Событие обновления булевого значения.
     * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
     */
    data class UpdateBooleanEvent(
        val value: Boolean,
        val type: ToggleType
    ): SettingsEvents() {
        /**
         * @brief Типы переключателей.
         * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
         */
        enum class ToggleType {
            THEME,
            WEIGHT
        }
    }
}