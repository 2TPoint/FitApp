package ru.points.fitapp.ui.settings.component

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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

) : ViewModel(), EventListener {
    private val _state = MutableStateFlow(SettingsState())

    val state = _state.asStateFlow()

    /**
     * @brief Обрабатывает события настроек.
     *
     * Обрабатывает события, связанные с изменением настроек, такие как переключение темы и единиц измерения веса.
     *
     * @param event Событие настроек.
     */
    override fun handle(event: Event) {
        when (event) {
            is SettingsEvents.UpdateBooleanEvent -> {
                when (event.type) {
                    SettingsEvents.UpdateBooleanEvent.ToggleType.WEIGHT -> {
                        _state.update {
                            it.copy(
                                isKg = event.value
                            )
                        }
                    }

                    SettingsEvents.UpdateBooleanEvent.ToggleType.THEME -> {
                        _state.update {
                            it.copy(
                                isDarkThemeSelected = event.value
                            )
                        }
                    }
                }

            }
        }
    }
}