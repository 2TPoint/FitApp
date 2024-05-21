package ru.points.fitapp.data.retrofit.api.model

import com.example.surimusakotlin.domain.model.Branded
import com.example.surimusakotlin.domain.model.Common

data class FoodInstant(
    val branded: List<Branded>,
    val common: List<Common>
)