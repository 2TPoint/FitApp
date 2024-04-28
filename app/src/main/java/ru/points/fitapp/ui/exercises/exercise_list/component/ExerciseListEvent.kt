package ru.points.fitapp.ui.exercises.exercise_list.component

import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.utils.Event

/**
 * @file ExerciseListEvents.kt
 * @brief Sealed класс для событий списка упражнений.
 *
 * Этот sealed класс определяет различные события, которые могут произойти в контексте списка упражнений.
 * Он используется для обработки изменений состояния всплывающего окна и других событий, связанных с списком упражнений.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
sealed class ExerciseListEvent: Event {
    /**
     * Событие обновления состояния показа всплывающего окна.
     *
     * @param id Идентификатор упражнения, для которого обновляется состояние (опционально).
     * @param isShowed Новое состояние показа всплывающего окна.
     */
    data class UpdatePopupShowedState(
        val id: Long? = null,
        val isShowed: Boolean
    ): ExerciseListEvent()
}

/**
 * @file PopupEvents.kt
 * @brief Sealed класс для событий всплывающего окна.
 *
 * Этот sealed класс определяет различные события, которые могут произойти в контексте всплывающего окна.
 * Он используется для обработки изменений в полях формы и сохранения упражнения.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
sealed class PopupEvents: Event {
    /**
     * Событие обновления значения переключателя.
     *
     * @param value Новое значение переключателя.
     */
    data class UpdateToggle(
        val value: Boolean
    ): PopupEvents()

    /**
     * Событие обновления названия упражнения.
     *
     * @param value Новое значение названия.
     */
    data class UpdateName(
        val value: String
    ): PopupEvents()

    /**
     * Событие обновления описания упражнения.
     *
     * @param value Новое значение описания.
     */
    data class UpdateDescription(
        val value: String
    ): PopupEvents()

    /**
     * Событие обновления веса упражнения.
     *
     * @param value Новое значение веса.
     */
    data class UpdateWeight(
        val value: String
    ): PopupEvents()

    /**
     * Событие обновления типа упражнения.
     *
     * @param value Новый тип упражнения.
     */
    data class UpdateType(
        val value: Exercise.Type
    ): PopupEvents()

    /**
     * Событие сохранения упражнения.
     */
    data object SaveExercise: PopupEvents()
}
