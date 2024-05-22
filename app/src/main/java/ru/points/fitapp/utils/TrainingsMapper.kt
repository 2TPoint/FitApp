package ru.points.fitapp.utils

import ru.points.fitapp.data.entity.Training
import ru.points.fitapp.data.vo.TrainingVo
import ru.points.fitapp.utils.ExerciseMapper.mapToVo
import ru.points.fitapp.utils.ExerciseMapper.toExercise

object TrainingsMapper {

    fun Training.toVo(): TrainingVo {
        return TrainingVo(
            id = this.id,
            name = this.name,
            description = this.description,
            time = this.time,
            exercisesList = this.exercisesList.map { mapToVo(it) }.toMutableList()
        )
    }

    fun TrainingVo.toTraining(): Training {
        return Training(
            id = this.id,
            name = this.name,
            description = this.description,
            time = this.time,
            exercisesList = this.exercisesList.map { it.toExercise() }.toMutableList()
        )
    }
}