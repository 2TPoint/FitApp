package ru.points.fitapp.ui.main.trainings.components.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.points.fitapp.ui.main.trainings.components.TrainingEvent
import ru.points.fitapp.ui.main.trainings.components.TrainingPopUpEvents
import ru.points.fitapp.utils.Event

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTrainingScreen(onEvent: (Event) -> Unit) {
    ModalBottomSheet(onDismissRequest = { onEvent(TrainingEvent.UpdatePopupShowedState(isShowed = false)) }) {
        PopupData(onEvent = onEvent)
    }
}

@Composable
private fun PopupData(onEvent: (Event) -> Unit){
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Создайте тренировку",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Название тренировки", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    placeholder = { Text(text = "Название") }
                )
                Text(text = "Описание тренировки", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    placeholder = { Text(text = "Описание") }
                )
            }
        }

        Button(
            onClick = {
                onEvent(TrainingPopUpEvents.SaveChanges(name = name, description = description))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(50.dp)
        ) {
            Text(text = "Сохранить", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Button(
            onClick = {
                      onEvent(TrainingEvent.UpdatePopupShowedState(isShowed = false))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text(text = "Отмена", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}