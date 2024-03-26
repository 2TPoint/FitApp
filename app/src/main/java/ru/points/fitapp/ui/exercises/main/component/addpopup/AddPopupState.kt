package ru.points.fitapp.ui.exercises.main.component.addpopup

import ru.points.fitapp.data.entity.Exercise
import java.sql.Time

data class AddPopupState(
    val isShowed: Boolean = false,

    val name: String? = null,
    val description: String? = null,

    val weight: Double? = null,
    val useWeight:Boolean = false,

    val distance: Double? = null,
    val useDistance: Boolean = false,

    val time: Time? = null,
    val useTime: Boolean = false,

    val discriminator: Exercise.Type? = null
)
