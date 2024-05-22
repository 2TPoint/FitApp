
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.points.fitapp.data.vo.ExerciseVo
import ru.points.fitapp.data.vo.TrainingVo
import ru.points.fitapp.ui.main.trainings.components.CurrentTrainingViewModel

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CurrentTrainingScreen(
    viewModel: CurrentTrainingViewModel
) {
    val training: TrainingVo = viewModel.trainingState.collectAsState().value.training
    val exercises: List<ExerciseVo> = viewModel.trainingState.collectAsState().value.allExercises

    val scaffoldState = rememberScaffoldState()
    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            BottomSheetContent(
                exercises = exercises,
                onExerciseSelected = { exercise ->
                    // Здесь вы можете добавить выбранное упражнение в тренировку
//                    viewModel.addExerciseToTraining(exercise)
                    scope.launch {
                        modalBottomSheetState.hide()
                    }
                },
                onClose = {
                    scope.launch {
                        modalBottomSheetState.hide()
                    }
                }
            )
        }
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    scope.launch {
                        modalBottomSheetState.show()
                    }
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Exercise")
                }
            }
        ) {
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
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetContent(
    exercises: List<ExerciseVo>,
    onExerciseSelected: (ExerciseVo) -> Unit,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Выберите упражнение",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        exercises.forEach { exercise ->
            Text(
                text = exercise.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onExerciseSelected(exercise) }
                    .padding(vertical = 8.dp),
                style = MaterialTheme.typography.body1
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onClose,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Закрыть")
        }
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
