package ru.points.fitapp.ui.settings.component

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.points.fitapp.utils.Event
import ru.points.fitapp.utils.EventListener

class SettingsViewModel(

) : ViewModel(), EventListener {
    private val _state = MutableStateFlow(SettingsState())

    val state = _state.asStateFlow()

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