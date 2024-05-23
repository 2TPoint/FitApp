package ru.points.fitapp.ui.main.exercises.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.points.fitapp.data.vo.ExerciseVo
import ru.points.fitapp.ui.main.exercises.component.ExerciseEvent
import ru.points.fitapp.ui.main.exercises.component.ExerciseListEvent
import ru.points.fitapp.ui.main.trainings.components.CurrentTrainingEvents
import ru.points.fitapp.utils.Event

@Composable
fun ExercisesScreen(
    list: List<ExerciseVo>,
    onEvent: (Event) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier
    )
    {
        items(
            items = list,
            key = { item -> item.id }
        ) { item ->
            ExerciseListItem(
                exerciseVo = item,
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
                    .height(70.dp),
                onEvent = onEvent
            )
        }
    }
}

/**
 * @file MainScreenController.kt
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
fun ExerciseListItem(
    exerciseVo: ExerciseVo,
    modifier: Modifier = Modifier,
    showArrow: Boolean = true,
    showBinIcon: Boolean = true,
    inTrainingMode: Boolean = false,
    position: Int = 0,
    onEvent: (Event) -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp,
        modifier = modifier,
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
                    .background(color = exerciseVo.color)
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
                        text = exerciseVo.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (exerciseVo.description.isNotEmpty()) {
                        Text(
                            text = exerciseVo.description,
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
                    if (exerciseVo.upNextTime) RoundTimeParam(time = "10")
                    if (exerciseVo.value.isNotEmpty()) {
                        Text(
                            modifier = Modifier
                                .height(15.dp)
                                .width(70.dp),
                            textAlign = TextAlign.Start,
                            fontSize = 11.sp,
                            text = exerciseVo.value,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                }
            }
            if (showBinIcon)

                IconButton(onClick = {
                    if (!inTrainingMode)
                        onEvent(ExerciseEvent.DeleteExercise(exerciseVo.id))
                    else
                        onEvent(CurrentTrainingEvents.DeleteSelectedExercise(position))
                }
                ) {
                    Icon(
                        modifier = Modifier
                            .weight(1f)
                            .size(width = 29.dp, height = 29.dp),
                        imageVector = Icons.Default.DeleteOutline,
                        contentDescription = ""
                    )
                }
        }
    }
}

/**
 * @file MainScreenController.kt
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
    androidx.compose.material3.Card(
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