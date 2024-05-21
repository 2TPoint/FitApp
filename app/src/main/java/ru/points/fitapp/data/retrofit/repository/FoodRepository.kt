package ru.points.fitapp.data.retrofit.repository

import com.example.surimusakotlin.data.api.RetrofitFoodInstance

import ru.points.fitapp.data.retrofit.api.model.FoodInstant
import ru.points.fitapp.data.retrofit.api.model.Nutrition
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Response
import ru.points.fitapp.data.retrofit.FoodRepositoryInterface

class FoodRepository : FoodRepositoryInterface{
    override suspend fun getFood(query: String): Response<FoodInstant>{
        return RetrofitFoodInstance.api.searchFood(query)
    }

    override suspend fun getNutritions(query: String): Response<Nutrition> {
        val jsonObject = JSONObject()
        jsonObject.put("query", query)
        val requestBody = jsonObject
            .toString().toRequestBody("application/json".toMediaTypeOrNull())

        val response = RetrofitFoodInstance.api.fetchNutrients(requestBody)

        return response
    }
}