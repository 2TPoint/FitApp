package com.example.surimusakotlin.data.api

import ru.points.fitapp.data.retrofit.api.model.FoodInstant
import ru.points.fitapp.data.retrofit.api.model.Nutrition
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiFoodService {

    @Headers(
        "Content-Type: application/json",
        "x-app-id: f55b564e",
        "x-app-key: ebb09c5dc3cbef9a264656d6332c1935"
    )
    @POST("v2/natural/nutrients")
    suspend fun fetchNutrients(@Body body: RequestBody):
            Response<Nutrition>

    @Headers(
        "Content-Type: application/json",
        "x-app-id: f55b564e",
        "x-app-key: ebb09c5dc3cbef9a264656d6332c1935"
    )
    @GET("v2/search/instant/")
    suspend fun searchFood(
        @Query("query") query: String
    ): Response<FoodInstant>


}