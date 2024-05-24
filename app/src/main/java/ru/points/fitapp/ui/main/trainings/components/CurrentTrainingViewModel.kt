package ru.points.fitapp.ui.main.trainings.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.points.fitapp.data.vo.TrainingVo
import ru.points.fitapp.domain.exercises.use_case_interface.GetExercisesUseCase
import ru.points.fitapp.domain.trainings.interfaces.AddNewExerciseToTrainingUseCase
import ru.points.fitapp.domain.trainings.interfaces.DeleteSelectedExerciseUseCase
import ru.points.fitapp.domain.trainings.interfaces.GetTrainingByIdUseCase
import ru.points.fitapp.ui.main.trainings.components.states.CurrentTrainingState
import ru.points.fitapp.utils.Event
import ru.points.fitapp.utils.EventListener

class CurrentTrainingViewModel(
    private val addNewExerciseToTrainingUseCase: AddNewExerciseToTrainingUseCase,
    private val getTrainingById: GetTrainingByIdUseCase,
    private val getExercisesUseCase: GetExercisesUseCase,
    private val deleteSelectedExerciseUseCase: DeleteSelectedExerciseUseCase,
    private val currentId: Long
) : ViewModel(), EventListener {

    private val _training = getTrainingById.handle(id = currentId)
    private val _exercises = getExercisesUseCase.handle()

    val trainingState = combine(
        _training,
        _exercises
    ) { training, exercises ->
        CurrentTrainingState(
            training = TrainingVo(
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
        val currentState = trainingState.value
        when (event) {
            is CurrentTrainingEvents.AddNewExercise -> addNewExercise(event)
            is CurrentTrainingEvents.DeleteSelectedExercise -> deleteSelectedExersiceFromTraining(event)
        }
    }

    private fun addNewExercise(event: CurrentTrainingEvents.AddNewExercise) {
        viewModelScope.launch(Dispatchers.IO) {
            addNewExerciseToTrainingUseCase.handle(event.training, event.id)
        }
    }

    private fun deleteSelectedExersiceFromTraining(event: CurrentTrainingEvents.DeleteSelectedExercise){
        viewModelScope.launch(Dispatchers.IO) {
            deleteSelectedExerciseUseCase.handle(trainingState.value.training, event.pos.toLong())
        }
    }

}

