package ru.points.fitapp.ui.main.trainings.components

import ru.points.fitapp.data.vo.ExerciseVo
import ru.points.fitapp.data.vo.TrainingVo
import ru.points.fitapp.utils.Event

sealed class CurrentTrainingEvents: Event {

    data class AddNewExercise(val training: TrainingVo, val exercise: ExerciseVo): Event

    data class DeleteExercise(val id: Int, val trainingId: Int): Event

    data class StartTraining(val trainingId: Int): Event

    data class StopTraining(val trainingId: Int): Event

}