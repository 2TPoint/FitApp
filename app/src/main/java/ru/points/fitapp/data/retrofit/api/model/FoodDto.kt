package ru.points.fitapp.data.retrofit.api.model

import android.os.Parcelable
import com.example.surimusakotlin.domain.model.Photo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FoodDto(
    val food_name: String,
    val nf_calories: Double,
    val nf_cholesterol: Double,//холестерин
    val nf_dietary_fiber: Double,//Пищевая клетчатка
    val nf_potassium: Double,//калий
    val nf_protein: Double,//белок
    val nf_saturated_fat: Double,//Насыщенные жиры
    val nf_sodium: Double,//натрий
    val nf_sugars: Double,//Сахар
    val nf_total_carbohydrate: Double,
    val nf_total_fat: Double,//Общие жиры
    val photo: Photo,
    val serving_weight_grams: Double,
): Parcelable

fun FoodDto.mapToOtherFood() : ru.points.fitapp.data.entity.Food {
    return ru.points.fitapp.data.entity.Food(
        name = food_name,
        weight = serving_weight_grams,
        calories = nf_calories,
        fats = nf_total_fat,
        carbohydrates = nf_total_carbohydrate,
        proteins = nf_protein
    )
}