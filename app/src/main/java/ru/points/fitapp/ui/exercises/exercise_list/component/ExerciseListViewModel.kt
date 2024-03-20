package ru.points.fitapp.ui.exercises.exercise_list.component

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
import ru.points.fitapp.data.entity.Exercise
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

            is PopupEvents.UpdateDescription -> {
                updatePopupStateDescription(value = event.value)
            }

            is PopupEvents.UpdateWeight -> {
                updatePopupStateWeight(value = event.value)
            }

            is PopupEvents.SaveExercise -> {
                upsertExercise()
            }

            is PopupEvents.UpdateType -> {
                updatePopupStateType(value = event.value)
            }
        }
    }

    private fun updatePopupStateType(
        value: Exercise.Type
    ) {
        _popupState.update {
            it.copy(type = value)
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
                        description = exercise.description,
                        weight = if (exercise.weight == null) "0" else exercise.weight.toString(),
                        type = exercise.type
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

    private fun updatePopupStateDescription(value: String) {
        _popupState.update {
            it.copy(description = value)
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
                    description = _popupState.value.description,
                    weight = _popupState.value.weight.toFloatOrNull(),
                    upNextTime = _popupState.value.upNextTime,
                    type = _popupState.value.type
                )
            } else {
                updateExerciseUseCase.handle(
                    id = _popupState.value.selectedId!!,
                    title = _popupState.value.name,
                    description = _popupState.value.description,
                    weight = _popupState.value.weight.toFloatOrNull(),
                    upNextTime = _popupState.value.upNextTime,
                    type = _popupState.value.type
                )
            }
        }
        _showPopup.update { false }
    }
}