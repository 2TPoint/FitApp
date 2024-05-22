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
import ru.points.fitapp.domain.trainings.interfaces.AddTraining
import ru.points.fitapp.domain.trainings.interfaces.GetTrainingByIdUseCase
import ru.points.fitapp.domain.trainings.interfaces.GetTrainingsUseCase
import ru.points.fitapp.ui.main.trainings.components.states.TrainingPopupState
import ru.points.fitapp.ui.main.trainings.components.states.TrainingsListState
import ru.points.fitapp.utils.Event
import ru.points.fitapp.utils.EventListener

class TrainingsViewModel(
    private val addTrainingsUseCase: AddTraining,
    private val getTrainingsUseCase: GetTrainingsUseCase,
    private val getTrainingByIdUseCase: GetTrainingByIdUseCase
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

        when(event) {

            is TrainingEvent.UpdatePopupShowedState -> updatePopUpShowState(event)

            is TrainingPopUpEvents.SaveChanges -> saveTraining(event)

        }

    }

    private fun updatePopUpShowState(event: TrainingEvent.UpdatePopupShowedState) {
        _showPopup.update { event.isShowed }
        if (event.isShowed) {
            viewModelScope.launch(Dispatchers.IO) {
                if (event.id == null)
                    preparePopupForCreate()
                else
                    openPopup(event.id)
            }
        }
    }

    private fun preparePopupForCreate() {
        _popupState.update {
            TrainingPopupState()
        }
    }

    private fun openPopup(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            getTrainingByIdUseCase.handle(id = id).collect { training ->
                _popupState.update {
                    TrainingPopupState(
                        id = it.id,
                        name = it.name,
                        description = it.description
                    )
                }
            }
        }
    }

    private fun saveTraining(event: TrainingPopUpEvents.SaveChanges) {
        viewModelScope.launch(Dispatchers.IO) {
            addTrainingsUseCase.handle(name = event.name, description = event.description)
        }
    }

}