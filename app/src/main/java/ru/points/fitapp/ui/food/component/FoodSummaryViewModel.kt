package ru.points.fitapp.ui.food.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.points.fitapp.data.darasource.dao.FoodDao
import ru.points.fitapp.utils.Event
import ru.points.fitapp.utils.EventListener

class FoodSummaryViewModel(
    foodDao: FoodDao
) : ViewModel(), EventListener {

    val state = foodDao.getAllFoods()
        .map { list ->
            FoodSummaryState(
                calories = list.sumOf { food -> food.calories },
                fats = list.sumOf { food -> food.fats },
                proteins = list.sumOf { food -> food.proteins },
                carbohydrates = list.sumOf { food -> food.carbohydrates },
            )
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            FoodSummaryState()
        )

    override fun handle(event: Event) {}

}