package ru.points.fitapp.ui.food.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.points.fitapp.ui.food.component.FoodSummaryViewModel

@Composable
fun FoodSummaryScreenController(
    viewModel: FoodSummaryViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
) {
}