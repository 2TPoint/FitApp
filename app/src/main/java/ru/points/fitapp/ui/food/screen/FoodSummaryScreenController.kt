package ru.points.fitapp.ui.food.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel
import ru.points.fitapp.R
import ru.points.fitapp.navigation.Destinations.FOOD_SEARCH_PAGE
import ru.points.fitapp.ui.food.component.FoodSummaryState
import ru.points.fitapp.ui.food.component.FoodSummaryViewModel

@Composable
fun FoodSummaryScreenController(
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FoodSummaryViewModel = koinViewModel(),
) {
    FoodSummaryScreen(
        state = viewModel.state.collectAsState().value,
        navigateTo = navigateTo,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FoodSummaryScreen(
    state: FoodSummaryState,
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.todays_products_consumed),
                        fontSize = 24.sp, fontWeight = FontWeight.Medium
                    )
                },
                actions = {
                    IconButton(onClick = { navigateTo(FOOD_SEARCH_PAGE) }) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = null
                        )
                    }
                },
                modifier = Modifier.background(color = Color.Transparent)
            )

            Spacer(Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = 5.dp
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(stringResource(R.string.statistic), fontSize = 24.sp, color = Color.White)

                    Spacer(Modifier.height(24.dp))

                    CircularProgressIndicator(
                        progress = 1f,
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = 8.dp,
                        modifier = Modifier.size(200.dp)
                    )

                    Spacer(Modifier.height(16.dp))

                    Text(
                        stringResource(R.string.calories_consumed, state.calories),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        SummaryItem(
                            label = stringResource(R.string.carbohydrates),
                            value = "${state.carbohydrates}г"
                        )
                        SummaryItem(
                            label = stringResource(R.string.fats),
                            value = "${state.proteins}г"
                        )
                        SummaryItem(
                            label = stringResource(R.string.proteins),
                            value = "${state.fats}г"
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SummaryItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Text(label, color = MaterialTheme.colorScheme.secondary, fontSize = 14.sp)
    }
}