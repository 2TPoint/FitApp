package ru.points.fitapp.ui.main.trainings.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.points.fitapp.data.vo.TrainingVo
import ru.points.fitapp.ui.main.trainings.components.item.TrainingProgramItem
import ru.points.fitapp.utils.Event

@Composable
fun TrainingProgramsScreen(
    list: List<TrainingVo>,
    onEvent: (Event) -> Unit,
    modifier: Modifier,
    navController: NavController
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 50.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier
    ) {
        items (
            items = list,
            key = {item -> item.id}
        ){ item->
            TrainingProgramItem(
                training = item,
                navController = navController
            )
        }
    }
}