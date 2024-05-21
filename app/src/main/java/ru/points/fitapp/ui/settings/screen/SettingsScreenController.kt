package ru.points.fitapp.ui.settings.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.points.fitapp.ui.settings.component.SettingsEvents
import ru.points.fitapp.ui.settings.component.SettingsState
import ru.points.fitapp.ui.settings.component.SettingsViewModel
import ru.points.fitapp.utils.Event

/**
 * @file SettingsScreenController.kt
 * @brief Контроллер экрана настроек.
 *
 * Отображает экран настроек и обрабатывает события, связанные с изменением настроек.
 *
 * @param modifier Модификатор для настройки внешнего вида экрана.
 * @param viewModel ViewModel для управления состоянием настроек.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
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

/**
 * @file SettingsScreenController.kt
 * @brief Экран настроек.
 *
 * Отображает элемент управления для настроек приложения.
 *
 * @param state Текущее состояние настроек.
 * @param onEvent Функция для обработки событий настроек.
 * @param modifier Модификатор для настройки внешнего вида экрана.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
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
        Box(
            modifier = Modifier
                .padding(top = 60.dp)
                .align(Alignment.CenterHorizontally)
                .background(color = Color.LightGray, shape = RoundedCornerShape(50))
                .size(170.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyHorizontalGrid(
            rows = GridCells.FixedSize(70.dp),
            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    color = Color.LightGray, shape = RoundedCornerShape(10)
                )
        ) {
            items(30) {
                Achievement()
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        ToggleOption(
            name = "Использовать темную тему",
            value = state.isDarkThemeSelected,
            backgroundColor = Color.Cyan,
            onEvent = onEvent,
            toggleType = SettingsEvents.UpdateBooleanEvent.ToggleType.THEME,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        )

        ToggleOption(
            name = "Использовать кг",
            value = state.isKg,
            backgroundColor = Color.Cyan,
            onEvent = onEvent,
            toggleType = SettingsEvents.UpdateBooleanEvent.ToggleType.WEIGHT,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

    }
}

/**
 * @file SettingsScreenController.kt
 * @brief Компонент достижения.
 *
 * Отображает визуальное представление достижения.
 *
 * @param modifier Модификатор для настройки внешнего вида компонента.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
@Composable
private fun Achievement(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .background(color = Color.Gray, shape = RoundedCornerShape(50))
            .size(70.dp)
    )
}

/**
 * @file SettingsScreenController.kt
 * @brief Компонент переключателя настроек.
 *
 * Отображает переключатель для изменения настроек приложения.
 *
 * @param name Название настройки.
 * @param value Текущее значение настройки.
 * @param backgroundColor Цвет фона компонента.
 * @param onEvent Функция для обработки событий настроек.
 * @param toggleType Тип переключателя.
 * @param modifier Модификатор для настройки внешнего вида компонента.
 *
 * @author Шмаков Ф.М., Демин И.А., Хоров Н.М.
 */
@Composable
private fun ToggleOption(
    name: String,
    value: Boolean,
    backgroundColor: Color,
    onEvent: (Event) -> Unit,
    toggleType: SettingsEvents.UpdateBooleanEvent.ToggleType,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(20.dp))
            .padding(vertical = 10.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name
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
            }
        )
    }
}