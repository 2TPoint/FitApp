package ru.points.fitapp.ui.main.trainings.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.points.fitapp.data.vo.ExerciseVo
import ru.points.fitapp.data.vo.TrainingVo
import ru.points.fitapp.ui.main.trainings.components.CurrentTrainingViewModel

@Composable
fun CurrentTrainingScreen(
    viewModel : CurrentTrainingViewModel
) {
    val training: TrainingVo = viewModel.trainingsState.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = training.name,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = training.description,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Программа тренировки",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        training.exercisesList.forEach { step ->
            WorkoutStepItem(step)
            if (step != training.exercisesList.last()) {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun WorkoutStepItem(exercise: ExerciseVo) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = exercise.title, style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Bold)
            if (!exercise.description.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = exercise.description, style = MaterialTheme.typography.body2)
            }
        }
    }
}
