package ru.points.fitapp.ui.main.trainings.components.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.points.fitapp.data.vo.TrainingVo


@Composable
fun TrainingProgramItem(training: TrainingVo) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle click */ }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column {
                Text(text = training.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(
                    text = training.description,
                    fontSize = 12.sp
                )
                Text(text = training.time, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.Gray)
            }
            Box(
                modifier = Modifier
                    .size(70.dp, 30.dp)
                    .background(Color.Yellow, shape = RoundedCornerShape(50))
                    .clickable { /* Handle click */ },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Перейти", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}