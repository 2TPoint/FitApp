package ru.points.fitapp.ui.exercises.popup.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.points.fitapp.R
import ru.points.fitapp.ui.exercises.main.component.addpopup.AddPopupEvent
import ru.points.fitapp.ui.exercises.main.component.addpopup.AddPopupState
import ru.points.fitapp.utils.Event

@Composable
fun AddPopup(
    state: AddPopupState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        OutlinedTextField(
            value = state.name ?: "",
            onValueChange = { onEvent(AddPopupEvent.InputName(it)) },
            label = { Text(text = stringResource(id = R.string.exercise_name)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Ascii
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = state.description ?: "",
            onValueChange = { onEvent(AddPopupEvent.InputDescription(it)) },
            label = { Text(text = stringResource(id = R.string.exercise_description)) },
            maxLines = 4,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Ascii
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        ToggleOption(
            name = stringResource(id = R.string.weight_exercise),
            value = state.useWeight,
            onClick = { onEvent(AddPopupEvent.SwitchWeightUsing) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = state.weight?.toString() ?: "",
            onValueChange = { onEvent(AddPopupEvent.InputWeight(it)) },
            label = { Text(text = stringResource(id = R.string.weight)) },
            singleLine = true,
            enabled = state.useWeight,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        ToggleOption(
            name = stringResource(id = R.string.cardio_exercise),
            value = state.useDistance,
            onClick = { onEvent(AddPopupEvent.SwitchDistanceUsing) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = state.distance?.toString() ?: "",
            onValueChange = { onEvent(AddPopupEvent.InputDistance(it)) },
            label = { Text(text = stringResource(id = R.string.distance)) },
            singleLine = true,
            enabled = state.useDistance,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
        )

        Spacer(modifier = Modifier.height(20.dp))

        ToggleOption(
            name = stringResource(id = R.string.use_time),
            value = state.useTime,
            onClick = { onEvent(AddPopupEvent.SwitchTimeUsing) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = state.time?.toString() ?: "",
            onValueChange = { onEvent(AddPopupEvent.InputTime(it)) },
            label = { Text(text = stringResource(id = R.string.time)) },
            singleLine = true,
            enabled = state.useTime,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = { onEvent(AddPopupEvent.Save) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(
                text = stringResource(id = R.string.save),
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun ToggleOption(
    name: String,
    value: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .height(40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name
        )

        Switch(
            checked = value,
            onCheckedChange = { onClick() },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddPopupPreview() {
//    AddPopup(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 20.dp)
//    )
}