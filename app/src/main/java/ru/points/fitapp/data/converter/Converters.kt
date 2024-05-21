package ru.points.fitapp.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.points.fitapp.data.entity.Exercise
import java.sql.Time

class Converters {
    @TypeConverter
    fun fromExerciseList(value: List<Exercise>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Exercise>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toExerciseList(value: String): List<Exercise> {
        val gson = Gson()
        val type = object : TypeToken<List<Exercise>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun longToTime(value: Long?): Time? {
        return value?.let { Time(value) }
    }

    @TypeConverter
    fun timeToLong(value: Time?): Long? {
        return value?.time
    }
}