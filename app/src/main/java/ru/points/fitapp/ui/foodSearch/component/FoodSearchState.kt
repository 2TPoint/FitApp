package ru.points.fitapp.ui.foodSearch.component

import ru.points.fitapp.data.entity.Food

data class FoodSearchState(
    val list: List<Food> = emptyList()
)