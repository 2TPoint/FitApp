package ru.points.fitapp.ui.exercises.main.component

import android.util.Log
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
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.domain.exercises.usecase.interfaces.GetExerciseByIdUseCase
import ru.points.fitapp.domain.exercises.usecase.interfaces.GetExercisesUseCase
import ru.points.fitapp.domain.exercises.usecase.interfaces.InsertExerciseUseCase
import ru.points.fitapp.domain.exercises.usecase.interfaces.UpdateExerciseUseCase
import ru.points.fitapp.ui.exercises.main.component.addpopup.AddPopupEvent
import ru.points.fitapp.ui.exercises.main.component.addpopup.AddPopupState
import ru.points.fitapp.ui.exercises.main.component.viewpopup.ViewPopupEvent
import ru.points.fitapp.ui.exercises.main.component.viewpopup.ViewPopupState
import ru.points.fitapp.utils.Event
import ru.points.fitapp.utils.EventListener
import java.sql.Time

class ExerciseListViewModel(
    getExercisesUseCase: GetExercisesUseCase,
    private val getExerciseByIdUseCase: GetExerciseByIdUseCase,
    private val insertExerciseUseCase: InsertExerciseUseCase,
    private val updateExerciseUseCase: UpdateExerciseUseCase
) : ViewModel(), EventListener {

    private val _list = getExercisesUseCase.handle()

    private val _viewPopupState = MutableStateFlow(ViewPopupState())

    private val _addPopupState = MutableStateFlow(AddPopupState())

    val state = combine(
        _list,
        _viewPopupState,
        _addPopupState
    ) { list, viewPopupState, addPopupState ->
        ExerciseListState(
            list = list,
            viewPopupState = viewPopupState,
            addPopupState = addPopupState
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ExerciseListState()
    )

    override fun handle(event: Event) {
        when (event) {
            is AddPopupEvent -> handleAddPopupEvents(event)
            is ViewPopupEvent -> handleViewPopupEvents(event)
            is ExerciseEvent.SelectCard -> {
                handleViewPopupEvents(ViewPopupEvent.ChangeVisibility)
                viewModelScope.launch(Dispatchers.IO) {
                    getExerciseByIdUseCase.handle(event.id).collect { exercise ->
                        _viewPopupState.update { state ->
                            state.copy(
                                id = exercise.id,
                                name = exercise.title,
                                description = exercise.description,
                                upNextTime = exercise.upNextTime,
                                weight = exercise.value.takeIf { exercise.discriminator == Exercise.Type.STRENGTH },
                                distance = exercise.value.takeIf { exercise.discriminator == Exercise.Type.CARDIO },
                                time = exercise.time,
                                discriminator = exercise.discriminator
                            )
                        }
                    }
                }
            }
        }
    }

    private fun handleAddPopupEvents(event: AddPopupEvent) {
        when (event) {
            is AddPopupEvent.ChangeVisibility -> {
                changeAddPopupVisibility()
            }

            is AddPopupEvent.SwitchWeightUsing -> {
                changeSwitchWeight()
            }

            is AddPopupEvent.SwitchDistanceUsing -> {
                changeSwitchDistance()
            }

            is AddPopupEvent.SwitchTimeUsing -> {
                changeSwitchTime()
            }

            is AddPopupEvent.InputWeight -> {
                inputWeight(event.value)
            }

            is AddPopupEvent.InputDistance -> {
                inputDistance(event.value)
            }

            is AddPopupEvent.InputTime -> {
                inputTime(event.value)
            }

            is AddPopupEvent.InputName -> {
                inputName(event.name)
            }

            is AddPopupEvent.InputDescription -> {
                inputDescription(event.description)
            }

            is AddPopupEvent.Save -> {
                createExercise()
            }
        }
    }

    private fun handleViewPopupEvents(event: ViewPopupEvent) {
        when (event) {
            is ViewPopupEvent.ChangeVisibility -> {
                changeViewPopupVisibility()
            }

            is ViewPopupEvent.InputValue -> {
                inputViewPopupValue(event.value)
            }

            is ViewPopupEvent.UpNextTime -> {
                upNextTime()
            }

            is ViewPopupEvent.Save -> {
                updateExerciseUseCase
            }

            else -> {}
        }
    }

    private fun changeViewPopupVisibility() {
        _viewPopupState.update { state ->
            state.copy(
                isShowed = !state.isShowed,
            )
        }
        Log.d("Change", "View popup visibility changed to ${_viewPopupState.value.isShowed}")
    }

    private fun inputViewPopupValue(value: String) {
        _viewPopupState.update { state ->
            when (state.discriminator ?: return) {
                Exercise.Type.STRENGTH -> {
                    state.copy(weight = value.toDouble())
                }

                Exercise.Type.CARDIO -> {
                    state.copy(distance = value.toDouble())
                }
            }
        }
    }

    private fun upNextTime() {
        _viewPopupState.update { state ->
            state.copy(upNextTime = !state.upNextTime)
        }
    }

    private fun updateExercise() {
        viewModelScope.launch(Dispatchers.IO) {
            updateExerciseUseCase.handle(
                Exercise(
                    id = _viewPopupState.value.id,
                    title = _viewPopupState.value.name,
                    description = _viewPopupState.value.description,
                    value = if ((_viewPopupState.value.discriminator
                            ?: Exercise.Type.STRENGTH) == Exercise.Type.STRENGTH
                    ) _viewPopupState.value.weight ?: 0.0 else _viewPopupState.value.distance
                        ?: 0.0,
                    upNextTime = _viewPopupState.value.upNextTime,
                    discriminator = _viewPopupState.value.discriminator ?: Exercise.Type.STRENGTH,
                    time = _viewPopupState.value.time
                )
            )
        }
    }

    private fun createExercise() {
        viewModelScope.launch(Dispatchers.IO) {
            insertExerciseUseCase.handle(
                name = _addPopupState.value.name ?: "",
                description = _addPopupState.value.description,
                value = _addPopupState.value.weight ?: _addPopupState.value.distance ?: 0.0,
                discriminator = if (_addPopupState.value.useWeight) Exercise.Type.STRENGTH else Exercise.Type.CARDIO,
                time = _addPopupState.value.time
            )
            _addPopupState.update { state ->
                state.copy(
                    isShowed = false,
                    name = null,
                    description = null,
                    weight = null,
                    useWeight = false,
                    distance = null,
                    useDistance = false,
                    time = null,
                    useTime = false
                )
            }
        }
    }

    private fun inputName(text: String) {
        _addPopupState.update { state ->
            state.copy(
                name = text
            )
        }
    }

    private fun inputDescription(text: String) {
        _addPopupState.update { state ->
            state.copy(
                description = text
            )
        }
    }

    private fun changeAddPopupVisibility() {
        _addPopupState.update { state ->
            state.copy(isShowed = !state.isShowed)
        }
        Log.d("Change", "Popup visibility changed to ${_addPopupState.value.isShowed}")
    }

    private fun changeSwitchWeight() {
        _addPopupState.update { state ->
            state.copy(
                useWeight = !state.useWeight,
                useDistance = false,
                useTime = false,
                distance = null,
                time = null
            )
        }
        Log.d(
            "Change", "Popup visibility changed to" +
                    "weight toggle: ${_addPopupState.value.useWeight}" +
                    "\ndistance toggle: ${_addPopupState.value.useDistance}" +
                    "\ntime toggle: ${_addPopupState.value.useTime}"
        )
    }

    private fun changeSwitchDistance() {
        _addPopupState.update { state ->
            state.copy(
                useWeight = false,
                useDistance = !state.useDistance,
                useTime = false,
                weight = null,
                time = null
            )
        }
        Log.d(
            "Change", "Popup visibility changed to" +
                    "weight toggle: ${_addPopupState.value.useWeight}" +
                    "\ndistance toggle: ${_addPopupState.value.useDistance}" +
                    "\ntime toggle: ${_addPopupState.value.useTime}"
        )
    }

    private fun changeSwitchTime() {
        _addPopupState.update { state ->
            state.copy(
                useWeight = false,
                useDistance = false,
                useTime = !state.useTime,
                weight = null,
                distance = null
            )
        }
        Log.d(
            "Change", "Popup visibility changed to" +
                    "weight toggle: ${_addPopupState.value.useWeight}" +
                    "\ndistance toggle: ${_addPopupState.value.useDistance}" +
                    "\ntime toggle: ${_addPopupState.value.useTime}"
        )
    }

    private fun inputWeight(text: String) {
        _addPopupState.update { state ->
            state.copy(
                weight = text.toDouble()
            )
        }
    }

    private fun inputDistance(text: String) {
        _addPopupState.update { state ->
            state.copy(
                distance = text.toDouble()
            )
        }
    }

    private fun inputTime(text: String) {
        _addPopupState.update { state ->
            state.copy(
                time = Time(text.toLong())
            )
        }
    }
}