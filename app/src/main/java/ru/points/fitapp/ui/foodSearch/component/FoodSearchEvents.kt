package ru.points.fitapp.ui.foodSearch.component

import ru.points.fitapp.utils.Event

sealed class FoodSearchEvents: Event {

    data class MakeRequest(
        val query: String
    ) : FoodSearchEvents()

}