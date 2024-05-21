package ru.points.fitapp.domain.exercises.usecase

import retrofit2.Response
import ru.points.fitapp.data.entity.Food
import ru.points.fitapp.data.retrofit.FoodRepositoryInterface
import ru.points.fitapp.data.retrofit.api.model.FoodInstant

class GetFoodInstantResponceByQueryUseCase(private val foodRepositoryInterface: FoodRepositoryInterface) {

    suspend operator fun invoke(query: String): Result<FoodInstant?>{
        return try {
            val response: Response<FoodInstant> = foodRepositoryInterface.getFood(query)
            if (!response.isSuccessful){
             return Result.failure(Exception("Error fetching Food Instant data"))
            }
            Result.success(response.body())
        }catch (e: Exception){
            Result.failure(e)
        }
    }

}