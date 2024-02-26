package ru.points.fitapp.ui.exercises.exercise_list.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import ru.points.fitapp.domain.use_case_interface.GetExercisesUseCase

class ExerciseListViewModel(
    private val useCase: GetExercisesUseCase
) : ViewModel() {

    private val _list = useCase.handle()

    val state = combine(
        _list
    ) { list ->
        ExerciseListState(
            list = list[0]
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ExerciseListState()
    )
}