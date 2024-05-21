package ru.points.fitapp.ui.exercises.exercise_list.screen

import android.content.res.Resources
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.data.vo.ExerciseVo
import ru.points.fitapp.ui.exercises.exercise_add_popup.screen.PopupScreenController
import ru.points.fitapp.ui.exercises.exercise_list.component.ExerciseListEvent
import ru.points.fitapp.ui.exercises.exercise_list.component.ExerciseListState
import ru.points.fitapp.ui.exercises.exercise_list.component.ExerciseListViewModel
import ru.points.fitapp.utils.Event

@Preview(showBackground = true, backgroundColor = 0xFFFFFF, showSystemUi = true)
@Composable
fun Preview() {
    ExerciseListItem(
        title = "aaaaaaaaaaaaaa",
        description = "5 мин",
        modifier = Modifier
            .padding(all = 20.dp)
            .width(392.dp)
            .height(70.dp),
        weight = "20.5",
        upNextTime = false,
        color = Color.Red
    )
}

/**
 * @file ExercisesScreenController.kt
 * @brief Composable функция для отображения экрана списка упражнений.
 *
 * Эта функция использует ViewModel для управления состоянием списка упражнений и отображает
 * список упражнений, а также всплывающее окно для добавления или редактирования упражнения.
 *
 * @param modifier Модификатор для настройки внешнего вида.
 * @param viewModel ViewModel для управления состоянием списка упражнений.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
@Composable
fun ExercisesScreenController(
    modifier: Modifier = Modifier,
    viewModel: ExerciseListViewModel = koinViewModel()
) {
    ExercisesScreen(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::handle,
        modifier = modifier
    )
}

/**
 * @file ExercisesScreenController.kt
 * @brief Composable функция для отображения списка упражнений.
 *
 * Эта функция отображает список упражнений, используя LazyColumn для эффективного
 * отображения больших списков. Она также обрабатывает события, связанные с
 * добавлением или редактированием упражнения, через всплывающее окно.
 *
 * @param state Состояние списка упражнений.
 * @param onEvent Функция обратного вызова для обработки событий.
 * @param modifier Модификатор для настройки внешнего вида.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExercisesScreen(
    state: ExerciseListState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    Box(modifier = modifier) {
        LazyColumn(

            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 50.dp
            ),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        )
        {
            items(
                items = state.list,
                key = { item -> item.id }
            ) { item ->
                ExerciseListItem(
                    title = item.title,
                    description = item.description,
                    weight = item.value.toString(),
                    upNextTime = item.upNextTime,
                    color = item.color,
                    modifier = Modifier
                        .clickable {
                            onEvent(
                                ExerciseListEvent.UpdatePopupShowedState(
                                    item.id,
                                    isShowed = true
                                )
                            )
                        }
                        .fillMaxWidth()
                        .height(70.dp))
            }
        }

        FloatingActionButton(
            onClick = { onEvent(ExerciseListEvent.UpdatePopupShowedState(null, isShowed = true)) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 120.dp, end = 30.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
    }

    if (state.isPopupShowed) {
        PopupScreenController(
            sheetState = modalBottomSheetState,
            onEvent = onEvent,
            popupState = state.popupState,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/**
 * @file ExercisesScreenController.kt
 * @brief Composable функция для отображения элемента списка упражнений.
 *
 * Эта функция отображает информацию об одном упражнении, включая его название,
 * описание, вес и флаг "Следующее". Она также позволяет пользователю
 * нажать на элемент списка для редактирования упражнения.
 *
 * @param title Название упражнения.
 * @param description Описание упражнения.
 * @param weight Вес упражнения.
 * @param upNextTime Флаг, указывающий, должно ли упражнение быть добавлено в список "Следующее".
 * @param color Цвет фона элемента списка.
 * @param modifier Модификатор для настройки внешнего вида.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
@Composable
private fun ExerciseListItem(
    title: String,
    description: String,
    weight: String,
    upNextTime: Boolean,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(20.dp)
                    .background(
                        color = color
                    )
            )
            Column(
                modifier = Modifier
                    .weight(4f)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {

                Row(
                    modifier = Modifier
                        .height(26.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        textAlign = TextAlign.Start,
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (description.isNotEmpty()) {
                        Text(
                            text = description,
                            textAlign = TextAlign.Start,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    if (upNextTime) RoundTimeParam(time = "10")
                    if (weight.isNotEmpty()) {
                        Text(
                            modifier = Modifier
                                .height(15.dp)
                                .width(70.dp),
                            textAlign = TextAlign.Start,
                            fontSize = 11.sp,
                            text = weight,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                }
            }
            Icon(
                modifier = Modifier
                    .weight(1f)
                    .size(width = 29.dp, height = 29.dp),
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = ""
            )

        }
    }
}

/**
 * @file ExercisesScreenController.kt
 * @brief Composable функция для отображения параметра времени в круге.
 *
 * Эта функция отображает параметр времени в виде круглого значка с текстом.
 *
 * @param time Время для отображения.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
@Composable
fun RoundTimeParam(
    time: String
) {
    Card(
        modifier = Modifier
            .width(70.dp)
            .height(15.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(Color.Yellow),
    ) {
        Box(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = time,
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}