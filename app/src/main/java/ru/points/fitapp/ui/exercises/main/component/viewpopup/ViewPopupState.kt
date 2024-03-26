package ru.points.fitapp.ui.exercises.main.component.viewpopup

import ru.points.fitapp.data.entity.Exercise
import java.sql.Time

data class ViewPopupState(
    val id: Long = 0,
    val isShowed: Boolean = false,
    val name: String = "",
    val upNextTime: Boolean = false,
    val description: String? = null,
    val weight: Double? = null,
    val distance: Double? = null,
    val time: Time? = null,
    val discriminator: Exercise.Type? = null
)