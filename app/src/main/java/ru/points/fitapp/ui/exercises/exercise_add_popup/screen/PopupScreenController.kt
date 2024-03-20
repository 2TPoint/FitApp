package ru.points.fitapp.ui.exercises.exercise_add_popup.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.points.fitapp.R
import ru.points.fitapp.data.entity.Exercise
import ru.points.fitapp.ui.exercises.exercise_list.component.PopupState
import ru.points.fitapp.ui.exercises.exercise_list.component.ExerciseListEvent
import ru.points.fitapp.ui.exercises.exercise_list.component.PopupEvents
import ru.points.fitapp.utils.Event

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Preview() {
    PopupScreenController(
        sheetState = SheetState(true, SheetValue.Expanded),
        onEvent = { println() },
        popupState = PopupState()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopupScreenController(
    sheetState: SheetState,
    onEvent: (Event) -> Unit,
    popupState: PopupState,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onEvent(ExerciseListEvent.UpdatePopupShowedState(isShowed = false)) },
        modifier = modifier
    ) {
        PopupScreen(
            state = popupState,
            onEvent = onEvent,
            modifier = modifier
        )
    }
}

@Composable
private fun PopupScreen(
    state: PopupState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val isMuscleExercise = remember {
        mutableStateOf(
            state.type != Exercise.Type.CARDIO
        )
    }
    Column(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(bottom = 50.dp)
            .padding(horizontal = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp),
            text = "Создайте упражнение",
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
        )
        OutlinedTextField(
            value = state.name,
            onValueChange = { onEvent(PopupEvents.UpdateName(it)) },
            singleLine = true,
            shape = RoundedCornerShape(corner = CornerSize(20.dp)),
            label = { Text(text = "Название упражнения") },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = state.description ?: "",
            onValueChange = { onEvent(PopupEvents.UpdateDescription(it)) },
            singleLine = true,
            shape = RoundedCornerShape(corner = CornerSize(20.dp)),
            label = { Text(text = "Описание упражнения") },
            modifier = Modifier
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 45.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    isMuscleExercise.value = !isMuscleExercise.value
                    onEvent(PopupEvents.UpdateType(Exercise.Type.STRENGTH))
                },
                modifier = Modifier
                    .width(58.dp)
                    .height(58.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 2.dp,
                        color = when (isMuscleExercise.value) {
                            true -> Color.Black
                            else -> Color.Transparent
                        },
                        shape = RoundedCornerShape(corner = CornerSize(20.dp))
                    )
                    .background(Color.Red)
                    .clickable(enabled = !isMuscleExercise.value){}

            ) {
                Icon(
                    modifier = Modifier
                        .width(26.dp)
                        .height(26.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.muscle),
                    contentDescription = null,
                    tint = Color.White
                )
            }

            IconButton(
                onClick = {
                    isMuscleExercise.value = !isMuscleExercise.value
                    onEvent(PopupEvents.UpdateType(Exercise.Type.CARDIO))
                },
                modifier = Modifier
                    .width(58.dp)
                    .height(58.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 2.dp,
                        color = when (!isMuscleExercise.value) {
                            true -> Color.Black
                            else -> Color.Transparent
                        },
                        shape = RoundedCornerShape(corner = CornerSize(20.dp))
                    )
                    .background(Color.Green)
                    .clickable(enabled = isMuscleExercise.value){}


            ) {
                Icon(
                    modifier = Modifier
                        .width(23.dp)
                        .height(31.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.running),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        OutlinedTextField(
            value = state.weight ?: "",
            onValueChange = { onEvent(PopupEvents.UpdateWeight(it)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            shape = RoundedCornerShape(corner = CornerSize(20.dp)),
            label = {
                Text(
                    text = when (isMuscleExercise.value) {
                        true -> "Используемый вес"
                        else -> "Расстояние"
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        Button(
            onClick = {
                onEvent(PopupEvents.SaveExercise)
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(
                text = "Сохранить",
                textAlign = TextAlign.Center
            )
        }
    }
}