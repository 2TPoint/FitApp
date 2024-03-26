@file:OptIn(ExperimentalMaterial3Api::class)

package ru.points.fitapp.ui.exercises.main.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel
import ru.points.fitapp.R
import ru.points.fitapp.ui.exercises.main.component.Exercise
import ru.points.fitapp.ui.exercises.main.component.ExerciseListState
import ru.points.fitapp.ui.exercises.main.component.ExerciseListViewModel
import ru.points.fitapp.ui.exercises.popup.add.AddPopup
import ru.points.fitapp.ui.exercises.popup.view.ViewPopup
import ru.points.fitapp.utils.Event

val list = listOf(
    Exercise(
        id = 1,
        name = "Анжумания",
        description = null,
        value = 123.0,
        time = null,
        improveValue = false
    ),
    Exercise(
        id = 2,
        name = "Анжумания",
        description = "Очень классное описание прям не могу",
        value = 123.0,
        time = null,
        improveValue = false
    ),
    Exercise(
        id = 3,
        name = "Анжумания",
        description = null,
        value = 123.0,
        time = 123,
        improveValue = false
    ),
    Exercise(
        id = 4,
        name = "Анжумания",
        description = null,
        value = 123.0,
        time = null,
        improveValue = true
    )
)

@Composable
fun ExercisesScreenController(
    modifier: Modifier = Modifier,
    viewModel: ExerciseListViewModel = koinViewModel()
) {
    val tabs = listOf(
        stringResource(id = R.string.programms),
        stringResource(id = R.string.exercises),
    )
    ExerciseScreen(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::handle,
        tabs = tabs,
        modifier = modifier
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun ExerciseScreen(
    state: ExerciseListState,
    onEvent: (Event) -> Unit,
    tabs: List<String>,
    modifier: Modifier = Modifier,
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState(initialPage = 0) { tabs.size }
    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }

    var showAddPopup by remember { mutableStateOf(false) }
    var showViewPopup by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
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
                .padding(horizontal = 20.dp)
        ) { index: Int ->
            when (index) {
                1 -> ExercisesVerticalGrid(list = list)
            }
        }
    }

    val viewPopupState = rememberModalBottomSheetState()

    if (showViewPopup) {
        showAddPopup = false
        ModalBottomSheet(
            sheetState = viewPopupState,
            onDismissRequest = { showViewPopup = false },
        ) {
            ViewPopup(
                name = "Какое-то упражнение",
                description = null,
                value = 123.321,
                type = "wew",
                improveValue = false,
                time = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 0.dp)
            )
        }
    }

    if (showAddPopup) {
        showAddPopup = false
        ModalBottomSheet(
            onDismissRequest = { showAddPopup = false }
        ) {
            AddPopup(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 0.dp)
            )
        }
    }
}


@Composable
fun ExerciseCard(
    name: String,
    description: String?,
    value: String,
    time: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    improveValue: Boolean = false
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        onClick = onClick,
        modifier = modifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 7.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 15.dp)
        ) {
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

            Text(
                text = description ?: stringResource(id = R.string.empty_description),
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                maxLines = 4,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )

            Text(
                text = value,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = if (improveValue) {
                    Modifier.background(color = Color.Green, shape = RoundedCornerShape(20.dp))
                } else {
                    Modifier.border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                }
                    .size(
                        width = 90.dp,
                        height = 26.dp
                    )
                    .wrapContentHeight(Alignment.CenterVertically)
            )

            time?.let {
                Text(
                    text = value,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(color = Color.Yellow, shape = RoundedCornerShape(20.dp))
                        .size(
                            width = 90.dp,
                            height = 26.dp
                        )
                        .wrapContentHeight(Alignment.CenterVertically)
                )
            }
        }
    }
}

@Composable
fun ExercisesVerticalGrid(
    list: List<Exercise>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier
    ) {
        items(
            items = list,
            key = { item -> item.id }
        ) { exercise ->
            ExerciseCard(
                name = exercise.name,
                description = exercise.description,
                value = exercise.value.toString(),
                time = exercise.time?.toString(),
                onClick = { /*TODO*/ },
                modifier = Modifier.size(width = 178.dp, height = 172.dp)
            )
        }
    }
}

@Preview(name = "Дефолт карточка")
@Composable
fun ExerciseCardPreviewFirst() {
    ExerciseCard(
        name = "Анжумания",
        value = "999.999 кг",
        description = "Какое-то описание кайфовое прям имба",
        time = null,
        onClick = {},
        modifier = Modifier.size(width = 178.dp, height = 172.dp)
    )
}

@Preview(name = "Карточка с бустом")
@Composable
fun ExerciseCardPreviewSecond() {
    ExerciseCard(
        name = "Анжумания",
        value = "999.999 кг",
        description = null,
        time = null,
        improveValue = true,
        onClick = {},
        modifier = Modifier.size(width = 178.dp, height = 172.dp)
    )
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun ExerciseScreenPreview() {
    ExerciseScreen(
        state = ExerciseListState(),
        onEvent = {},
        tabs = listOf(
            stringResource(id = R.string.programms),
            stringResource(id = R.string.exercises),
        ),
        modifier = Modifier
            .fillMaxWidth()
    )
}
