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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
    exercisesViewModel: ExerciseListViewModel = koinViewModel(),
    navController: NavController
) {
    MainScreen(
        trainingsListState = trainingsViewModel.trainingsState.collectAsState().value,
        exerciseListState = exercisesViewModel.exercisesState.collectAsState().value,
        onTrainingEvent = trainingsViewModel::handle,
        onExerciseEvent = exercisesViewModel::handle,
        tabs = listOf("Программы", "Упражнения"),
        modifier = modifier,
        navController = navController
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
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var selectedTabIndex by remember { mutableIntStateOf(TRAINING_LIST_INDEX) }

    val pagerState = rememberPagerState(
        initialPage = TRAINING_LIST_INDEX,
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

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp)
            ) { index: Int ->
                when (index) {
                    TRAINING_LIST_INDEX -> TrainingProgramsScreen(
                        list = trainingsListState.list,
                        onEvent = onTrainingEvent,
                        navController = navController,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 12.dp),
                    )

                    EXERCISES_LIST_INDEX -> ExercisesScreen(
                        list = exerciseListState.list,
                        onEvent = onExerciseEvent,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 12.dp)
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = {
                when (selectedTabIndex) {
                    TRAINING_LIST_INDEX -> onTrainingEvent(
                        TrainingEvent.UpdatePopupShowedState(
                            isShowed = true
                        )
                    )

                    EXERCISES_LIST_INDEX -> onExerciseEvent(
                        ExerciseListEvent.UpdatePopupShowedState(
                            null,
                            isShowed = true
                        )
                    )
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

    val trainingSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    if (trainingsListState.isPopupShowed) {
        AddTrainingScreen(
            sheetState = trainingSheetState,
            onEvent = onTrainingEvent
        )
    }

    val exerciseModalSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    if (exerciseListState.isPopupShowed) {
        PopupScreenController(
            sheetState = exerciseModalSheetState,
            onEvent = onExerciseEvent,
            popupState = exerciseListState.popupState,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

const val TRAINING_LIST_INDEX = 0
const val EXERCISES_LIST_INDEX = 1
