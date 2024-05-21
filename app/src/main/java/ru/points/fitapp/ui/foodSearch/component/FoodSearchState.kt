package ru.points.fitapp.ui.foodSearch.component

import ru.points.fitapp.data.entity.Food

data class FoodSearchState(
    val list: List<Food> = listOf(
        Food(
            name = "wewewe",
            weight = 1.0,
            calories = 1L,
            carbohydrates = 1L,
            fats = 1L,
            proteins = 1L
        )
    )
)