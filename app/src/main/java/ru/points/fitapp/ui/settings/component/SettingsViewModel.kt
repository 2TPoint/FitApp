package ru.points.fitapp.ui.settings.component

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.points.fitapp.data.manager.PreferencesManager
import ru.points.fitapp.utils.Event
import ru.points.fitapp.utils.EventListener

/**
 * @file SettingsViewModel.kt
 * @brief ViewModel для настроек приложения.
 *
 * Этот ViewModel управляет состоянием настроек приложения и обрабатывает события, связанные с изменением настроек.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
class SettingsViewModel(
    private val preferencesManager: PreferencesManager
) : ViewModel(), EventListener {

    private val _state = MutableStateFlow(SettingsState(
        preferencesManager.isDark,
        preferencesManager.isKg,
        preferencesManager.isM
    ))

    val state get() = _state.asStateFlow()

    override fun handle(event: Event) {
        when (event) {
            is SettingsEvents.UpdateBooleanEvent -> {
                when (event.type) {
                    SettingsEvents.UpdateBooleanEvent.ToggleType.WEIGHT -> {
                        _state.update { it.copy(isKg = !event.value) }
                        preferencesManager.isKg = !event.value
                    }

                    SettingsEvents.UpdateBooleanEvent.ToggleType.THEME -> {
                        _state.update { it.copy(isDarkThemeSelected = event.value) }
                        preferencesManager.isDark = event.value
                    }

                    SettingsEvents.UpdateBooleanEvent.ToggleType.DISTANCE -> {
                        _state.update { it.copy(isM = !event.value) }
                        preferencesManager.isM = !event.value
                    }
                }
            }
        }
    }
}