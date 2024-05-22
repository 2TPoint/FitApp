package ru.points.fitapp.ui.main.trainings.components.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.points.fitapp.data.vo.TrainingVo
import ru.points.fitapp.navigation.Destinations


@Composable
fun TrainingProgramItem(
    training: TrainingVo,
    navController: NavController
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate("${Destinations.CURRENT_TRAINING_PAGE}/${training.id.toLong()}") }
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = training.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Text(
                    text = training.description,
                    fontSize = 12.sp
                )

                Text(
                    text = training.time,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Icon(
                modifier = Modifier
                    .size(width = 29.dp, height = 29.dp),
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = null
            )
        }
    }
}