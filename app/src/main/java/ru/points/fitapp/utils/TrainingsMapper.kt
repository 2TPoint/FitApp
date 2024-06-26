package ru.points.fitapp.utils

import ru.points.fitapp.data.entity.Training
import ru.points.fitapp.data.vo.ExerciseVo
import ru.points.fitapp.data.vo.TrainingVo

object TrainingsMapper {

    fun Training.toVo(): TrainingVo {
        return TrainingVo(
            id = this.id,
            name = this.name,
            description = this.description,
            time = this.time,
            exercisesList = mutableListOf<ExerciseVo>()
        )
    }

    fun TrainingVo.toTraining(): Training {
        return Training(
            id = this.id,
            name = this.name,
            description = this.description,
            time = this.time,
            exercisesList = this.exercisesList.map { it.id }.toMutableList()
        )
    }
}