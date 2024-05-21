package ru.points.fitapp.ui.exercises.popup.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.points.fitapp.R
import ru.points.fitapp.ui.exercises.main.component.viewpopup.ViewPopupEvent
import ru.points.fitapp.ui.exercises.main.component.viewpopup.ViewPopupState
import ru.points.fitapp.ui.exercises.popup.add.ToggleOption
import ru.points.fitapp.utils.Event

@Composable
fun ViewPopup(
    state: ViewPopupState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {

        Text(
            text = state.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = state.description ?: stringResource(id = R.string.empty_description),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            maxLines = 4,
            modifier = Modifier
                .fillMaxWidth()
                .height(88.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = state.weight?.toString() ?: state.distance?.toString() ?: "",
            onValueChange = { onEvent(ViewPopupEvent.InputValue(it)) },
            label = {
                Text(text = stringResource(id = R.string.weight))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        ToggleOption(
            name = stringResource(id = R.string.up_next_time),
            value = state.upNextTime,
            onClick = { onEvent(ViewPopupEvent.UpNextTime) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        ToggleOption(
            name = stringResource(id = R.string.use_time),
            value = false,
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = "",
            onValueChange = { onEvent(ViewPopupEvent.InputValue(it)) },
            label = { Text(text = stringResource(id = R.string.time)) },
            singleLine = true,
            enabled = false,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        Spacer(modifier = Modifier.height(60.dp))

        Button(
            onClick = { onEvent(ViewPopupEvent.Save) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(
                text = stringResource(id = R.string.save),
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(
                text = stringResource(id = R.string.edit),
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ViewPopupPreview() {
//    ViewPopup(
//        name = "Такое-то упражнение",
//        description = null,
//        value = 123.323,
//        type = "qwq",
//        improveValue = false,
//        time = null,
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 20.dp)
//    )
}