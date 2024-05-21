package ru.points.fitapp.ui.foodSearch.screen

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel
import ru.points.fitapp.ui.food.component.FoodSummaryViewModel

@Composable
fun FoodSummaryScreenController(
    viewModel: FoodSummaryViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
) {
    val searchQuery = remember { mutableStateOf(TextFieldValue("")) }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF121212))) {
        TopAppBar(
            title = {
                TextField(
                    value = searchQuery.value,
                    onValueChange = { searchQuery.value = it },
                    placeholder = { Text("Search") },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        backgroundColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            backgroundColor = Color(0xFF1E1E1E),
            actions = {
                TextButton(onClick = { /* Handle Refresh */ }) {
                    Text("Обновить", color = Color.White)
                }
            }
        )
        Spacer(Modifier.height(10.dp))
        SearchResultList()
    }
}

@Composable
fun SearchResultList() {
    // Dummy data for the list
    val items = listOf(
        FoodItem("apple", "182.0 гр.", "94.6 ккал", "25.1гр.", "0.5гр.", "0.3гр."),
        FoodItem("apples", "182.0 гр.", "94.6 ккал", "25.1гр.", "0.5гр.", "0.3гр."),
        FoodItem("appletini", "84.8 гр.", "149.1 ккал", "6.7гр.", "0.0гр.", "0.0гр."),
        FoodItem("apple pie", "125.0 гр.", "296.3 ккал", "42.5гр.", "2.4гр.", "13.8гр.")
    )

    LazyColumn {
        items.forEach() { item ->
            FoodCard(item)
        }
    }
}

@Composable
fun FoodCard(foodItem: FoodItem) {
    Card(
        backgroundColor = Color(0xFF2E3B55),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(foodItem.name, color = Color.White, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(4.dp))
            Text("${foodItem.weight} | ${foodItem.calories}", color = Color.White)
            Spacer(modifier = Modifier.height(4.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Углеводы: ${foodItem.carbs}", color = Color.Gray)
                Text("Белки: ${foodItem.protein}", color = Color.Gray)
                Text("Жиры: ${foodItem.fat}", color = Color.Gray)
            }
            Spacer(modifier = Modifier.height(8.dp))
            IconButton(onClick = { /* Handle add */ }, modifier = Modifier.align(Alignment.End)) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
            }
        }
    }
}

data class FoodItem(
    val name: String,
    val weight: String,
    val calories: String,
    val carbs: String,
    val protein: String,
    val fat: String
)

@Preview(showBackground = true)
@Composable
fun PreviewSearchScreen() {
    PreviewSearchScreen()
}








