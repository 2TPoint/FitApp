package ru.points.fitapp.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.sql.Time

class Converters {

    @TypeConverter
    fun longToTime(value: Long?): Time? {
        return value?.let { Time(value) }
    }

    @TypeConverter
    fun timeToLong(value: Time?): Long? {
        return value?.time
    }

    @TypeConverter
    fun fromLongList(value: List<Long>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Long>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toLongList(value: String): List<Long> {
        val gson = Gson()
        val type = object : TypeToken<List<Long>>() {}.type
        return gson.fromJson(value, type)
    }
}
