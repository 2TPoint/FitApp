package ru.points.fitapp.ui.exercises.exercise_add_popup.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.points.fitapp.ui.exercises.exercise_list.component.PopupState
import ru.points.fitapp.ui.exercises.exercise_list.component.ExerciseListEvent
import ru.points.fitapp.ui.exercises.exercise_list.component.PopupEvents
import ru.points.fitapp.utils.Event

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

    Column(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = state.name,
            onValueChange = { onEvent(PopupEvents.UpdateName(it)) },
            singleLine = true,
            label = { Text(text = "Название упражнения") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Использовать вес"
            )

            Switch(
                checked = state.isWeightUsed,
                onCheckedChange = {
                    onEvent(PopupEvents.UpdateToggle(it))
                }
            )
        }

        OutlinedTextField(
            value = state.weight,
            onValueChange = { onEvent(PopupEvents.UpdateWeight(it)) },
            singleLine = true,
            enabled = state.isWeightUsed,
            label = { Text(text = "Вес") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                onEvent(PopupEvents.SaveExercise)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "Сохранить",
                textAlign = TextAlign.Center
            )
        }
    }
}