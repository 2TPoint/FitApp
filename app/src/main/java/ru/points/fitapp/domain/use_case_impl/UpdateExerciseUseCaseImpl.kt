package ru.points.fitapp.domain.use_case_impl

import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.repository.irepository.ExerciseRepository
import ru.points.fitapp.domain.use_case_interface.UpdateExerciseUseCase

/**
 * @file UpdateExerciseUseCaseImpl.kt
 * @brief Реализация use case для обновления упражнения.
 *
 * Этот класс предоставляет реализацию для обновления упражнения в репозитории по его уникальному идентификатору.
 * Он использует параметры упражнения для обновления существующего экземпляра Exercise в репозитории.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
class UpdateExerciseUseCaseImpl(
    private val exerciseRepository: ExerciseRepository
): UpdateExerciseUseCase {
    /**
     * Обновляет упражнение в репозитории.
     *
     * Использует переданные параметры для обновления существующего упражнения в репозитории.
     *
     * @param id Уникальный идентификатор упражнения.
     * @param title Заголовок упражнения.
     * @param description Описание упражнения.
     * @param weight Вес упражнения.
     * @param upNextTime Флаг, указывающий, должно ли упражнение быть добавлено в список "Следующее".
     * @param type Тип упражнения.
     */
    override fun handle(id: Long, title: String,
                        description: String?,
                        weight: Float?,
                        upNextTime: Boolean,
                        type: Exercise.Type) {
        exerciseRepository.updateExercise(
            id = id,
            title = title,
            description = description,
            weight = weight,
            upNextTime = upNextTime,
            type = type
        )
    }
}