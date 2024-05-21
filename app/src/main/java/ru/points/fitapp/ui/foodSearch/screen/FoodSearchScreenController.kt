package ru.points.fitapp.ui.foodSearch.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.points.fitapp.R
import ru.points.fitapp.data.entity.Food
import ru.points.fitapp.ui.foodSearch.component.FoodSearchEvents
import ru.points.fitapp.ui.foodSearch.component.FoodSearchState
import ru.points.fitapp.ui.foodSearch.component.FoodSearchViewModel
import ru.points.fitapp.utils.Event

@Composable
fun FoodSearchScreenController(
    modifier: Modifier = Modifier,
    viewModel: FoodSearchViewModel = koinViewModel(),
) {
    FoodSearchScreen(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::handle,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
private fun FoodSearchScreen(
    state: FoodSearchState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    val searchQuery = remember { mutableStateOf("") }
    Column(
        modifier = modifier
    ) {

        OutlinedTextField(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            label = { Text(text = stringResource(id = R.string.Search)) },
            keyboardActions = KeyboardActions(
                onSearch = { onEvent(FoodSearchEvents.MakeRequest(searchQuery.value)) }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp, start = 12.dp, end = 12.dp),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onEvent(FoodSearchEvents.MakeRequest(searchQuery.value))
                    }
                )
            }
        )

        Spacer(Modifier.height(10.dp))

        LazyColumn {
            items(items = state.list) { item ->
                FoodCard(item, onEvent)
            }
        }
    }
}


@Composable
fun FoodCard(
    foodItem: Food,
    onEvent: (Event) -> Unit
) {
    Card(
        backgroundColor = Color(0xFF2E3B55),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = foodItem.name,
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${foodItem.weight} | ${foodItem.calories}",
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.carbonates, foodItem.carbohydrates),
                    color = Color.Gray
                )

                Text(
                    text = stringResource(R.string.fates, foodItem.proteins),
                    color = Color.Gray
                )

                Text(
                    text = stringResource(R.string.proteines, foodItem.fats),
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            IconButton(
                onClick = { onEvent(FoodSearchEvents.Save(foodItem)) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
            }
        }
    }
}







