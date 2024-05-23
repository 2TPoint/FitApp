package ru.points.fitapp.ui.main.trainings.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.points.fitapp.data.vo.TrainingVo
import ru.points.fitapp.ui.main.trainings.components.TrainingEvent
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
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier
    ) {
        items (
            items = list,
            key = {item -> item.id}
        ){ item->
            TrainingProgramItem(
                training = item,
                navController = navController,
                modifier = Modifier
                    .clickable {
                        onEvent(
                            TrainingEvent.UpdatePopupShowedState(
                                item.id.toLong(),
                                isShowed = true
                            )
                        )
                    }
                    .fillMaxWidth()
                    .height(70.dp)
            )
        }
    }
}