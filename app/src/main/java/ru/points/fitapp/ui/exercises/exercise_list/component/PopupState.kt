package ru.points.fitapp.ui.exercises.exercise_list.component

import ru.points.fitapp.data.entity.Exercise

/**
 * Состояние всплывающего окна для добавления или редактирования упражнения.
 *
 * Этот data класс содержит данные, необходимые для отображения и обработки всплывающего окна,
 * связанного с добавлением или редактированием упражнения.
 *
 * @property selectedId Идентификатор выбранного упражнения (опционально).
 * @property name Название упражнения.
 * @property description Описание упражнения (опционально).
 * @property upNextTime Флаг, указывающий, должно ли упражнение быть добавлено в список "Следующее".
 * @property weight Вес упражнения.
 * @property type Тип упражнения.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
data class PopupState(
    val selectedId: Long? = null,
    val name: String = "",
    val description: String? = null,
    val upNextTime: Boolean = false,
    val weight: String = "",
    val type: Exercise.Type = Exercise.Type.STRENGTH
)
