package ru.points.fitapp.domain.use_case_impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.points.fitapp.data.repository.irepository.ExerciseRepository
import ru.points.fitapp.data.vo.ExerciseVo
import ru.points.fitapp.domain.use_case_interface.GetExercisesUseCase
import ru.points.fitapp.utils.ExerciseMapper

class GetExerciseUseCaseImpl(
    private val exerciseRepository: ExerciseRepository
) : GetExercisesUseCase {
    override fun handle(): Flow<List<ExerciseVo>> {
        return exerciseRepository.getExercises().map { list ->
            list.map { item ->
                ExerciseMapper.mapToVo(item)
            }
        }
    }
}