package ru.points.fitapp.ui.foodSearch.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.points.fitapp.domain.exercises.usecase.GetNutritionsForCommonListUseCase
import ru.points.fitapp.utils.Event
import ru.points.fitapp.utils.EventListener

class FoodSearchViewModel(
    private val getNutritionsForCommonListUseCase: GetNutritionsForCommonListUseCase
) : ViewModel(), EventListener {
    private val _state = MutableStateFlow(FoodSearchState())

    val state get() = _state.asStateFlow()

    override fun handle(event: Event) {
        when (event) {
            is FoodSearchEvents.MakeRequest -> {
                updateList(request = event.query)
            }
        }
    }

    private fun updateList(request: String) {
        viewModelScope.launch {
            getNutritionsForCommonListUseCase
                .invoke(request)
                .collect { list ->
                    _state.update {
                        FoodSearchState(list)
                    }
                }
        }
    }
}