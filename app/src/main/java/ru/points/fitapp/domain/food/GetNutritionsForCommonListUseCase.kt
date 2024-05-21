package ru.points.fitapp.domain.food

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.withContext
import ru.points.fitapp.data.entity.Food
import ru.points.fitapp.data.retrofit.FoodRepositoryInterface
import ru.points.fitapp.data.retrofit.api.model.FoodDto
import ru.points.fitapp.data.retrofit.api.model.mapToOtherFood

class GetNutritionsForCommonListUseCase(
    private val foodRepository: FoodRepositoryInterface,
    private val getFoodInstantResponceByQueryUseCase: GetFoodInstantResponceByQueryUseCase
) {

    suspend operator fun invoke(query: String): Flow<List<Food>> {
        return channelFlow {
            withContext(Dispatchers.Default) {
                try {
                    val res = getFoodInstantResponceByQueryUseCase.invoke(query)
                    if (!res.isSuccess) throw Exception("БЛЯТЬ ПИЗДА")
                    val foodList = res.getOrNull()?.common ?: throw Exception("БЛЯТЬ ПИЗДА2")

                    val tempMutableList = mutableListOf<FoodDto>()

                    foodList.take(4).forEach { common ->
                        val additionalInfo = foodRepository.getNutritions(query = common.food_name)

                        if (!additionalInfo.isSuccessful) throw Exception("Error fetching nutritions data")

                        additionalInfo.body()?.foods?.first()?.let {
                            tempMutableList.add(it)
                        }
                    }

                    val list = tempMutableList.map { food -> food.mapToOtherFood() }
                    send(list)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

}