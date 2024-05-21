package ru.points.fitapp.data.darasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.points.fitapp.data.entity.Food

@Dao
interface FoodDao {
    @Query("select * from food")
    fun getAllFoods(): Flow<List<Food>>

    @Insert
    fun insertFood(food: Food)
}