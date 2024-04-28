package ru.points.fitapp.domain.use_case_interface

import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.ui.exercises.exercise_list.component.PopupEvents

/**
 * @file InsertExerciseUseCase.kt
 * @brief Интерфейс use case для вставки нового упражнения.
 *
 * Этот интерфейс определяет контракт для вставки нового упражнения в репозиторий.
 * Он использует параметры упражнения для создания нового экземпляра Exercise и вставки его в репозиторий.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
interface InsertExerciseUseCase {
    /**
     * Вставляет новое упражнение в репозиторий.
     *
     * Создает новый экземпляр Exercise с переданными параметрами и вставляет его в репозиторий.
     *
     * @param title Заголовок упражнения.
     * @param description Описание упражнения.
     * @param weight Вес упражнения.
     * @param upNextTime Флаг, указывающий, должно ли упражнение быть добавлено в список "Следующее".
     * @param type Тип упражнения.
     */
    fun handle(title: String,
               description: String?,
               weight: Float?,
               upNextTime: Boolean,
               type: Exercise.Type)
}