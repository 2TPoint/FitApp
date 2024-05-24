package ru.points.fitapp.domain.trainings.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import ru.points.fitapp.data.repository.irepository.ExerciseRepository
import ru.points.fitapp.data.repository.repository.TrainingsRepository
import ru.points.fitapp.data.vo.ExerciseVo
import ru.points.fitapp.data.vo.TrainingVo
import ru.points.fitapp.domain.trainings.interfaces.GetTrainingByIdUseCase
import ru.points.fitapp.utils.ExerciseMapper.mapToVo

class GetTrainingByIdUseCaseImpl(
    private val trainingsRepository: TrainingsRepository,
    private val exerciseRepository: ExerciseRepository
) : GetTrainingByIdUseCase {
    override fun handle(id: Long): Flow<TrainingVo> = flow {
        trainingsRepository.getTrainingById(id).collect { training ->
            if (training != null) {
                val exercises = mutableListOf<ExerciseVo>()
                for (exerciseId in training.exercisesList) {
                    val exercise = exerciseRepository.getExercise(exerciseId).firstOrNull()
                    exercise?.let {
                        exercises.add(mapToVo(it))
                    }
                }
                val trainingVo = TrainingVo(
                    id = training.id,
                    name = training.name,
                    description = training.description,
                    time = training.time,
                    exercisesList = exercises
                )
                emit(trainingVo)
            }
        }
    }
}