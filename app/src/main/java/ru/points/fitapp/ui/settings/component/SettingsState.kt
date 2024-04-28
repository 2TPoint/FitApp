package ru.points.fitapp.ui.settings.component

/**
 * @file SettingsState.kt
 * @brief Класс состояния настроек приложения.
 *
 * Этот класс представляет состояние настроек приложения, включая выбор темной темы и единиц измерения веса.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
data class SettingsState(
    val isDarkThemeSelected: Boolean = false,
    val isKg: Boolean = true
)
