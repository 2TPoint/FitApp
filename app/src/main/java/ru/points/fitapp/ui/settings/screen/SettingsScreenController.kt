package ru.points.fitapp.ui.settings.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel
import ru.points.fitapp.R
import ru.points.fitapp.ui.settings.component.SettingsEvents
import ru.points.fitapp.ui.settings.component.SettingsState
import ru.points.fitapp.ui.settings.component.SettingsViewModel
import ru.points.fitapp.utils.Event

@JvmInline
value class Variant(
    val value: String
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    val sampleState = SettingsState(
        isDarkThemeSelected = false,
        isKg = false
    )
    SettingsScreen(
        state = sampleState,
        onEvent = {},
        modifier = Modifier.fillMaxSize()
    )
}


@Composable
fun SettingsScreenController(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = koinViewModel()
) {
    SettingsScreen(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::handle,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 40.dp)
    )
}

@Composable
private fun SettingsScreen(
    state: SettingsState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = modifier,
    ) {

        ToggleOption(
            name = stringResource(R.string.use_funts),
            value = !state.isKg,
            onToggle = { value ->
                onEvent(
                    SettingsEvents.UpdateBooleanEvent(
                        value,
                        SettingsEvents.UpdateBooleanEvent.ToggleType.WEIGHT
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        ToggleOption(
            name = stringResource(R.string.use_miles),
            value = !state.isM,
            onToggle = { value ->
                onEvent(
                    SettingsEvents.UpdateBooleanEvent(
                        value,
                        SettingsEvents.UpdateBooleanEvent.ToggleType.DISTANCE
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        )


        ToggleOption(
            name = stringResource(id = R.string.use_dark_theme),
            value = state.isDarkThemeSelected,
            onToggle = { value ->
                onEvent(
                    SettingsEvents.UpdateBooleanEvent(
                        value,
                        SettingsEvents.UpdateBooleanEvent.ToggleType.THEME
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        SimpleOption(
            name = stringResource(id = R.string.about_app),
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        )

        SimpleOption(
            name = stringResource(id = R.string.delete_all_data),
            onClick = { /*TODO*/ },
            color = Color.Red,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun SimpleOption(
    name: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = Color.Black
) {
    Text(
        text = name,
        color = color,
        modifier = modifier
            .height(40.dp)
            .clickable { onClick() }
    )
}

@Composable
private fun ToggleOption(
    name: String,
    value: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.height(40.dp)
    ) {
        Text(text = name)

        Switch(
            checked = value,
            onCheckedChange = { newValue ->
                onToggle(newValue)
            }
        )
    }
}