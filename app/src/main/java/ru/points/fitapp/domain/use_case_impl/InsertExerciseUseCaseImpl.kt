package ru.points.fitapp.domain.use_case_impl

import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.repository.irepository.ExerciseRepository
import ru.points.fitapp.domain.use_case_interface.InsertExerciseUseCase

/**
 * @file InsertExerciseUseCaseImpl.kt
 * @brief Реализация use case для вставки нового упражнения.
 *
 * Этот класс предоставляет реализацию для вставки нового упражнения в репозиторий.
 * Он использует параметры упражнения для создания нового экземпляра Exercise и вставки его в репозиторий.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
class InsertExerciseUseCaseImpl(
    private val exerciseRepository: ExerciseRepository
) : InsertExerciseUseCase {
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
    override fun handle(
        title: String,
        description: String?,
        weight: Float?,
        upNextTime: Boolean,
        type: Exercise.Type
    ) {
        exerciseRepository.insertExercise(
            exercise = Exercise(
                title = title,
                description = description,
                weight = weight,
                upNextTime = upNextTime,
                type = type)
        )
    }
}