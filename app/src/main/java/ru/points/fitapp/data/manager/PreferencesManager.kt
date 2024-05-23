package ru.points.fitapp.data.manager

import android.content.Context
import android.content.Context.MODE_PRIVATE

class PreferencesManager(context: Context) {

    private val preferences = context.getSharedPreferences(PREFERENCES, MODE_PRIVATE)

    var isKg = preferences.getBoolean(WEIGHT_UNIT, true)
        set(value) {
            preferences.edit().putBoolean(WEIGHT_UNIT, value).apply()
            field = value
        }

    var isM = preferences.getBoolean(DISTANCE_UNIT, true)
        set(value) {
            preferences.edit().putBoolean(DISTANCE_UNIT, value).apply()
            field = value
        }


    var isDark = preferences.getBoolean(IS_DARK, false)
        set(value) {
            preferences.edit().putBoolean(IS_DARK, value).apply()
            field = value
        }




    private companion object {
        const val PREFERENCES = "preferences"
        const val WEIGHT_UNIT = "weight_unit"
        const val DISTANCE_UNIT = "distance_unit"
        const val IS_DARK = "is_dark"
    }
}