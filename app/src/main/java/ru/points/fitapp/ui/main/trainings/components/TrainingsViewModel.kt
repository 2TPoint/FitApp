package ru.points.fitapp.ui.main.trainings.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.points.fitapp.domain.trainings.interfaces.AddTraining
import ru.points.fitapp.domain.trainings.interfaces.DeleteTrainingUseCase
import ru.points.fitapp.domain.trainings.interfaces.GetTrainingByIdUseCase
import ru.points.fitapp.domain.trainings.interfaces.GetTrainingsUseCase
import ru.points.fitapp.domain.trainings.interfaces.UpdateTrainingInfoUseCase
import ru.points.fitapp.ui.main.trainings.components.states.TrainingPopupState
import ru.points.fitapp.ui.main.trainings.components.states.TrainingsListState
import ru.points.fitapp.utils.Event
import ru.points.fitapp.utils.EventListener

class TrainingsViewModel(
    private val addTrainingsUseCase: AddTraining,
    private val getTrainingsUseCase: GetTrainingsUseCase,
    private val getTrainingByIdUseCase: GetTrainingByIdUseCase,
    private val updateTrainingInfoUseCase: UpdateTrainingInfoUseCase,
    private val deleteTrainingUseCase: DeleteTrainingUseCase
) : ViewModel(), EventListener {

    private val _trainings = getTrainingsUseCase.handle()

    private val _showPopup = MutableStateFlow(false)

    private val _popupState = MutableStateFlow(TrainingPopupState())

    val trainingsState = combine(
        _trainings,
        _showPopup,
        _popupState
    ) { trainings, isPopupShowed, popup ->
        TrainingsListState(
            list = trainings,
            isPopupShowed = isPopupShowed,
            popupState = popup
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = TrainingsListState()
    )


    override fun handle(event: Event) {

        when (event) {

            is TrainingEvent.UpdatePopupShowedState -> {
                updatePopUpShowState(event)
            }

            is TrainingPopUpEvents.SaveChanges -> saveTraining(event)

            is TrainingPopUpEvents.DeleteTraining -> deleteTraining()
        }

    }

    private fun deleteTraining(){
        _showPopup.value = false
        viewModelScope.launch(Dispatchers.IO) {
            deleteTrainingUseCase.handle(_popupState.value.id!!.toLong())
        }
    }

    private fun updatePopUpShowState(event: TrainingEvent.UpdatePopupShowedState) {
        if (event.isShowed) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    if (event.id == null) {
                        preparePopupForCreate()
                    } else {
                        openPopup(event.id)
                    }
                }
            }
        }
        _showPopup.value = event.isShowed
    }

    private fun preparePopupForCreate() {
        _popupState.update {
            TrainingPopupState()
        }
    }

    private suspend fun openPopup(id: Long) {
        getTrainingByIdUseCase.handle(id = id).collect { training ->
            withContext(Dispatchers.Main) {
                _popupState.update {
                    TrainingPopupState(
                        id = training.id.toLong(),
                        name = training.name,
                        description = training.description
                    )
                }
            }
        }
    }

    private fun saveTraining(event: TrainingPopUpEvents.SaveChanges) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (_popupState.value.id == null)
                    addTrainingsUseCase.handle(name = event.name, description = event.description)
                else {
                    updateTrainingInfoUseCase.handle(
                        _popupState.value.id!!,
                        event.name,
                        event.description
                    )
                }
            }
        }
    }

}