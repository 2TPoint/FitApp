package ru.points.fitapp.ui.exercises.main.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.points.fitapp.domain.use_case_interface.GetExerciseUseCase
import ru.points.fitapp.domain.use_case_interface.GetExercisesUseCase
import ru.points.fitapp.domain.use_case_interface.InsertExerciseUseCase
import ru.points.fitapp.domain.use_case_interface.UpdateExerciseUseCase
import ru.points.fitapp.utils.Event
import ru.points.fitapp.utils.EventListener

class ExerciseListViewModel(
    private val getExercisesUseCase: GetExercisesUseCase,
    private val getExerciseUseCase: GetExerciseUseCase,
    private val insertExerciseUseCase: InsertExerciseUseCase,
    private val updateExerciseUseCase: UpdateExerciseUseCase
) : ViewModel(), EventListener {

    private val _list = getExercisesUseCase.handle()

    private val _showPopup = MutableStateFlow(false)

    private val _popupState = MutableStateFlow(PopupState())

    val state = combine(
        _list,
        _showPopup,
        _popupState
    ) { list, isPopupShowed, popup ->
        ExerciseListState(
            list = list,
            isPopupShowed = isPopupShowed,
            popupState = popup
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ExerciseListState()
    )

    override fun handle(event: Event) {
        when (event) {
            is ExerciseListEvent.UpdatePopupShowedState -> {
                updatePopupState(
                    isShowed = event.isShowed,
                    event = event
                )
            }

            is PopupEvents.UpdateName -> {
                updatePopupStateName(value = event.value)
            }

            is PopupEvents.UpdateToggle -> {
                updatePopupStateToggle(value = event.value)
            }

            is PopupEvents.UpdateWeight -> {
                updatePopupStateWeight(value = event.value)
            }

            is PopupEvents.SaveExercise -> {
                upsertExercise()
            }
        }
    }

    private fun updatePopupState(
        isShowed: Boolean,
        event: ExerciseListEvent.UpdatePopupShowedState
    ) {
        _showPopup.update { isShowed }
        if (isShowed) {
            viewModelScope.launch(Dispatchers.IO) {
                if (event.id == null) {
                    preparePopupForCreate()
                } else {
                    preparePopupForEdit(id = event.id)
                }
            }
        }
    }

    private fun preparePopupForEdit(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            getExerciseUseCase.handle(id = id).collect { exercise ->
                _popupState.update {
                    PopupState(
                        selectedId = id,
                        name = exercise.title,
                        isWeightUsed = exercise.weight != null,
                        weight = if (exercise.weight == null) "0" else exercise.weight.toString()
                    )
                }
            }
        }
    }

    private fun preparePopupForCreate() {
        _popupState.update {
            PopupState()
        }
    }

    private fun updatePopupStateName(value: String) {
        _popupState.update {
            it.copy(name = value)
        }
    }

    private fun updatePopupStateToggle(value: Boolean) {
        _popupState.update {
            it.copy(isWeightUsed = value)
        }
    }

    private fun updatePopupStateWeight(value: String) {
        _popupState.update {
            it.copy(weight = value)
        }
    }

    private fun upsertExercise() {
        CoroutineScope(Dispatchers.IO).launch {
            if (_popupState.value.selectedId == null) {
                insertExerciseUseCase.handle(
                    title = _popupState.value.name,
                    weight = _popupState.value.weight.toFloatOrNull()
                )
            } else {
                updateExerciseUseCase.handle(
                    id = _popupState.value.selectedId!!,
                    title = _popupState.value.name,
                    weight = _popupState.value.weight.toFloatOrNull()
                )
            }
        }
        _showPopup.update { false }
    }
}