package ru.points.fitapp.ui.main.exercises.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import ru.points.fitapp.ui.main.exercises.component.ExerciseListEvent
import ru.points.fitapp.utils.Event

@Composable
fun ExercisesScreen(
    list: List<ExerciseVo>,
    onEvent: (Event) -> Unit,
    modifier: Modifier
) {
    LazyColumn(

        contentPadding = PaddingValues(
            horizontal = 20.dp,
            vertical = 50.dp
        ),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    )
    {
        items(
            items = list,
            key = { item -> item.id }
        ) { item ->
            ExerciseListItem(
                title = item.title,
                description = item.description,
                weight = item.value,
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