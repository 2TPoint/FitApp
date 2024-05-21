package ru.points.fitapp.domain.use_case_impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.points.fitapp.data.repository.irepository.ExerciseRepository
import ru.points.fitapp.data.vo.ExerciseVo
import ru.points.fitapp.domain.use_case_interface.GetExercisesUseCase
import ru.points.fitapp.utils.ExerciseMapper

/**
 * @file GetExercisesUseCaseImpl.kt
 * @brief Реализация use case для получения списка упражнений.
 *
 * Этот класс предоставляет реализацию для получения списка упражнений из репозитория.
 * Он использует корутины для асинхронной обработки данных.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
class GetExercisesUseCaseImpl(
    private val exerciseRepository: ExerciseRepository
) : GetExercisesUseCase {
    /**
     * Получает список упражнений из репозитория и преобразует его в VO.
     *
     * Использует корутины для асинхронной обработки данных.
     * Преобразует каждый элемент списка упражнений в VO с помощью ExerciseMapper.
     *
     * @return Flow с списком упражнений в виде VO.
     */
    override fun handle(): Flow<List<ExerciseVo>> {
        return exerciseRepository.getExercises().map { list ->
            list.map { item ->
                ExerciseMapper.mapToVo(item)
            }
        }
    }
}