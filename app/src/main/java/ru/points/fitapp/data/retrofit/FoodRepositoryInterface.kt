package ru.points.fitapp.data.retrofit

import retrofit2.Response
import ru.points.fitapp.data.retrofit.api.model.FoodInstant
import ru.points.fitapp.data.retrofit.api.model.Nutrition

interface FoodRepositoryInterface {

    suspend fun getFood(query: String): Response<FoodInstant>

    suspend fun getNutritions(query: String): Response<Nutrition>

}