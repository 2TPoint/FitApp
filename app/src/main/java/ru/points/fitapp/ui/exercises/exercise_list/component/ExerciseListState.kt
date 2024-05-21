package ru.points.fitapp.ui.exercises.exercise_list.component

import ru.points.fitapp.data.vo.ExerciseVo

/**
 * @file ExerciseListState.kt
 * @brief Data класс для состояния списка упражнений.
 *
 * Этот data класс содержит состояние списка упражнений, включая список упражнений,
 * флаг показа всплывающего окна и состояние всплывающего окна.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
data class ExerciseListState(
    /**
     * Список упражнений в виде VO.
     */
    val list: List<ExerciseVo> = listOf(),

    /**
     * Флаг, указывающий, показано ли всплывающее окно.
     */
    val isPopupShowed: Boolean = false,

    /**
     * Состояние всплывающего окна.
     */
    val popupState: PopupState = PopupState()
)