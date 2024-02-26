package ru.points.fitapp.ui.exercises.exercise_list.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.points.fitapp.ui.exercises.exercise_list.component.ExerciseListViewModel

@Composable
fun ExercisesScreenController(
    modifier: Modifier = Modifier,
    viewModel: ExerciseListViewModel = koinViewModel()
) {
    ExercisesScreen(
        state = viewModel.state.collectAsState().value,
        modifier = modifier
    )
}