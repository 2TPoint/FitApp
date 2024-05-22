package ru.points.fitapp.ui.main.trainings.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import ru.points.fitapp.data.vo.TrainingVo
import ru.points.fitapp.domain.exercises.use_case_interface.GetExercisesUseCase
import ru.points.fitapp.domain.trainings.interfaces.AddNewExerciseToTrainingUseCase
import ru.points.fitapp.domain.trainings.interfaces.GetTrainingByIdUseCase
import ru.points.fitapp.ui.main.trainings.components.states.CurrentTrainingState
import ru.points.fitapp.utils.Event
import ru.points.fitapp.utils.EventListener

class CurrentTrainingViewModel(
    val addNewExerciseToTrainingUseCase: AddNewExerciseToTrainingUseCase,
    val getTrainingById: GetTrainingByIdUseCase,
    val getExercisesUseCase: GetExercisesUseCase,
    val currentId: Long
) : ViewModel(), EventListener {

    private val _training = getTrainingById.handle(id = currentId)

    private val _exercises = getExercisesUseCase.handle()

    val trainingState = combine(
        _training,
        _exercises
    ) {
        training, exercises ->
        CurrentTrainingState(
            training =
            TrainingVo(
                id = training.id,
                name = training.name,
                description = training.description,
                time = training.time,
                exercisesList = training.exercisesList
            ),
            allExercises = exercises
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = CurrentTrainingState()
    )

    override fun handle(event: Event) {

        when (event) {
            is CurrentTrainingEvents.AddNewExercise -> addNewExercise(event)
            is CurrentTrainingEvents.DeleteExercise -> deleteExercise(event)
            is CurrentTrainingEvents.StartTraining -> startTraining(event)
            is CurrentTrainingEvents.StopTraining -> stopTraining(event)
        }

    }


    private fun addNewExercise(event: CurrentTrainingEvents.AddNewExercise) {
        addNewExerciseToTrainingUseCase.handle(event.training, event.exercise)
    }

    private fun deleteExercise(event: CurrentTrainingEvents.DeleteExercise) {

    }

    private fun startTraining(event: CurrentTrainingEvents.StartTraining) {

    }

    private fun stopTraining(event: CurrentTrainingEvents.StopTraining) {

    }
}

