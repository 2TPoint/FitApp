package ru.points.fitapp.ui.exercises.exercise_list.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.points.fitapp.ui.exercises.exercise_list.component.ExerciseListState

@Composable
fun ExercisesScreen(
    state: ExerciseListState,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        LazyColumn(
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 50.dp
            )
        )
        {
            items(
                items = state.list,
                key = { item -> item.id }
            ) { item ->
                ExerciseListItem(
                    title = item.title,
                    subtitle = item.subtitle,
                    color = item.color,
                    icon = item.icon
                )
            }
        }

        FloatingActionButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 120.dp, end = 30.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
    }

}

@Composable
private fun ExerciseListItem(
    title: String,
    subtitle: String,
    color: Color,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(1.dp, color = Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            CircleIcon(
                color = color,
                icon = icon
            )
        }
    }
}

@Composable
fun CircleIcon(
    color: Color,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(20.dp),
                color = color
            )
            .padding(all = 5.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}