package ru.points.fitapp.ui.main.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.points.fitapp.ui.main.exercises.component.ExerciseListEvent
import ru.points.fitapp.ui.main.exercises.component.ExerciseListState
import ru.points.fitapp.ui.main.exercises.component.ExerciseListViewModel
import ru.points.fitapp.ui.main.exercises.component.PopupScreenController
import ru.points.fitapp.ui.main.exercises.screen.ExercisesScreen
import ru.points.fitapp.ui.main.trainings.components.TrainingEvent
import ru.points.fitapp.ui.main.trainings.components.TrainingsViewModel
import ru.points.fitapp.ui.main.trainings.components.item.AddTrainingScreen
import ru.points.fitapp.ui.main.trainings.components.states.TrainingsListState
import ru.points.fitapp.ui.main.trainings.screen.TrainingProgramsScreen
import ru.points.fitapp.utils.Event

@Composable
fun MainScreenController(
    modifier: Modifier = Modifier,
    trainingsViewModel: TrainingsViewModel = koinViewModel(),
    exercisesViewModel: ExerciseListViewModel = koinViewModel()
) {
    MainScreen(
        trainingsListState = trainingsViewModel.trainingsState.collectAsState().value,
        exerciseListState = exercisesViewModel.exercisesState.collectAsState().value,
        onTrainingEvent = trainingsViewModel::handle,
        onExerciseEvent = exercisesViewModel::handle,
        tabs = listOf("Программы", "Упражнения"),
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun MainScreen(
    trainingsListState: TrainingsListState,
    exerciseListState: ExerciseListState,
    onTrainingEvent: (Event) -> Unit,
    onExerciseEvent: (Event) -> Unit,
    tabs: List<String>,
    modifier: Modifier = Modifier
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { tabs.size }
    )

    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }

    var showViewPopup by remember { mutableStateOf(false) }
    var showAddTraining by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.fillMaxWidth()
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text = tab) }
                    )
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) { index: Int ->
            when (index) {
                0 -> TrainingProgramsScreen(
                    list = trainingsListState.list,
                    onEvent = onTrainingEvent,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp)
                )
                1 -> ExercisesScreen(
                    list = exerciseListState.list,
                    onEvent = onExerciseEvent,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp)
                )
            }
        }

        if (trainingsListState.isPopupShowed) {
            AddTrainingScreen(
                onEvent = onTrainingEvent
            )
        }

        if (exerciseListState.isPopupShowed) {
            PopupScreenController(
                sheetState = modalBottomSheetState,
                onEvent = onExerciseEvent,
                popupState = exerciseListState.popupState,
                modifier = Modifier.fillMaxWidth()
            )
        }

        FloatingActionButton(
            onClick = {
                if (selectedTabIndex == 0) {
                    onTrainingEvent(TrainingEvent.UpdatePopupShowedState(isShowed = true))
                } else {
                    onExerciseEvent(ExerciseListEvent.UpdatePopupShowedState(null, isShowed = true))
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 50.dp, end = 30.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
    }
}
