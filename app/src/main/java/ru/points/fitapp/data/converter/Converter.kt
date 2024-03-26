package ru.points.fitapp.data.converter

import androidx.room.TypeConverter
import java.sql.Time

class Converter {

    @TypeConverter
    fun longToTime(value: Long?): Time? {
        return value?.let { Time(value) }
    }

    @TypeConverter
    fun timeToLong(value: Time?): Long? {
        return value?.time
    }
}