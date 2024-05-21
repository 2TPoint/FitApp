package ru.points.fitapp.domain.use_case_interface

import ru.points.fitapp.data.entity.Exercise

/**
 * @file UpdateExerciseUseCase.kt
 * @brief Интерфейс use case для обновления упражнения.
 *
 * Этот интерфейс определяет контракт для обновления упражнения в репозитории по его уникальному идентификатору.
 * Он использует параметры упражнения для обновления существующего экземпляра Exercise в репозитории.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
interface UpdateExerciseUseCase {
    /**
     * Обновляет упражнение в репозитории.
     *
     * Использует переданные параметры для обновления существующего упражнения в репозитории.
     *
     * @param id Уникальный идентификатор упражнения.
     * @param title Заголовок упражнения.
     * @param description Описание упражнения.
     * @param weight Вес упражнения.
     * @param upNextTime Флаг, указывающий, должно ли упражнение быть усложнено в следующий раз.
     * @param type Тип упражнения.
     */
    fun handle(id: Long, title: String,
               description: String?,
               weight: Float?,
               upNextTime: Boolean,
               type: Exercise.Type)
}