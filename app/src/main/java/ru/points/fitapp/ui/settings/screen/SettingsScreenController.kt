package ru.points.fitapp.ui.settings.screen

import android.widget.ImageButton
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.points.fitapp.ui.settings.component.SettingsEvents
import ru.points.fitapp.ui.settings.component.SettingsState
import ru.points.fitapp.ui.settings.component.SettingsViewModel
import ru.points.fitapp.ui.theme.Blue80
import ru.points.fitapp.ui.theme.LightBlue80
import ru.points.fitapp.ui.theme.Pink80
import ru.points.fitapp.ui.theme.Purple80
import ru.points.fitapp.utils.Event


@Preview
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
    )
}

@Composable
private fun SettingsScreen(
    state: SettingsState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier.verticalScroll(scrollState),
    ) {
        Row(modifier = Modifier
            .padding(top = 20.dp, start = 20.dp, end = 10.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            Text(text = "Профиль")
            IconButton(onClick = { /*TODO*/ },) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "")
            }
        }
        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .align(Alignment.CenterHorizontally)
                .shadow(elevation = 20.dp, shape = RoundedCornerShape(50))
                .background(color = Pink80, shape = RoundedCornerShape(50))
                .size(170.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(200.dp)
                .background(color = Pink80, shape = RoundedCornerShape(10))
                .align(Alignment.CenterHorizontally)
        ) {
            Row(modifier = Modifier
                .padding(top = 5.dp, start = 20.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically){
                Text(text = "Достижения")
            }
            LazyHorizontalGrid(
                rows = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.align(Alignment.Center).padding(top = 20.dp)
            ) {
                items(8) { index ->
                    Column(
                        modifier = Modifier.size(60.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Achievement()
                    }
                }
            }
        }


        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier
            .padding(PaddingValues(20.dp))
            .background(color = LightBlue80, shape = RoundedCornerShape(20.dp))) {

            Spacer(modifier = Modifier.height(20.dp))

            ToggleOption(
                name = "Использовать темную тему",
                value = state.isDarkThemeSelected,
                onEvent = onEvent,
                toggleType = SettingsEvents.UpdateBooleanEvent.ToggleType.THEME,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )
            
            DividerLine()

            ToggleOption(
                name = "Использовать кг",
                value = state.isKg,
                onEvent = onEvent,
                toggleType = SettingsEvents.UpdateBooleanEvent.ToggleType.WEIGHT,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )

            DividerLine()

            SimpleOption(
                name = "Свойства",
                onEvent = onEvent,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )

            DividerLine()

            SimpleOption(
                name = "О FitApp",
                onEvent = onEvent,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))
        }


    }
}

@Composable
private fun Achievement(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .background(color = Color.Gray, shape = RoundedCornerShape(50))
            .size(60.dp)
    )
}


@Composable
private fun SimpleOption(
    name: String,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .height(46.dp)
            .padding(vertical = 0.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name
        )
    }
}
@Composable
private fun ToggleOption(
    name: String,
    value: Boolean,
    onEvent: (Event) -> Unit,
    toggleType: SettingsEvents.UpdateBooleanEvent.ToggleType,
    modifier: Modifier = Modifier
) {

        Row(
            modifier = modifier
                .height(46.dp)
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name
            )
            val switchColors = SwitchDefaults.colors(
                checkedThumbColor = Color.White, // Цвет переключателя в положении "включено"
                checkedTrackColor = Pink80, // Цвет фона переключателя в положении "включено"
                uncheckedThumbColor = Pink80, // Цвет переключателя в положении "выключено"
                uncheckedTrackColor = Color.White,// Цвет фона переключателя в положении "выключено"
                checkedBorderColor = Pink80,
                uncheckedBorderColor = Pink80
            )
            Switch(
                checked = value,
                onCheckedChange = {
                    onEvent(
                        SettingsEvents.UpdateBooleanEvent(
                            value = it,
                            type = toggleType
                        )
                    )
                },
                colors = switchColors
            )
        }

}


@Composable
private fun DividerLine() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawLine(
                color = Blue80,
                start = Offset(0f, size.height / 2),
                end = Offset(size.width, size.height / 2),
                strokeWidth = 4f,
                cap = StrokeCap.Round
            )
        }
    }
    
}