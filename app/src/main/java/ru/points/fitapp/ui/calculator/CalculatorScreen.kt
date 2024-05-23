package ru.points.fitapp.ui.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.points.fitapp.MainActivity
import ru.points.fitapp.R
import ru.points.fitapp.activity
import java.util.Locale

@Composable
fun CalculatorScreen() {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {


        val lander = remember { mutableStateOf("") }
        val landerResult = remember { mutableDoubleStateOf(0.0) }

        val coroutine = rememberCoroutineScope()

        Text(
            text = stringResource(
                R.string.max_weight_jim,
                String.format(Locale.ENGLISH, "%.3f", landerResult.doubleValue)
            ),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = lander.value,
            label = { Text(text = "Вес") },
            onValueChange = { lander.value = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                coroutine.launch {
                    withContext(Dispatchers.Default) {
                        landerResult.doubleValue =
                            (100 * lander.value.toDouble()) / (101.3 - 2.67123 * 8)
                    }
                }
            }),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
        )

        Spacer(modifier = Modifier.height(30.dp))

        val podtg = remember { mutableStateOf("") }
        val podtgResult = remember { mutableDoubleStateOf(0.0) }

        Text(
            text = stringResource(
                R.string.max_weight_podtg,
                String.format(Locale.ENGLISH, "%.3f", podtgResult.doubleValue)
            ),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = podtg.value,
            label = { Text(text = "Вес") },
            onValueChange = { podtg.value = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                coroutine.launch {
                    withContext(Dispatchers.Default) {
                        podtgResult.doubleValue =
                            (100 * podtg.value.toDouble()) / (101.3 - 2.67123 * 8)
                    }
                }
            }),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
        )

        Spacer(modifier = Modifier.height(30.dp))

        val prisyad = remember { mutableStateOf("") }
        val prisyadResult = remember { mutableDoubleStateOf(0.0) }

        Text(
            text = stringResource(
                R.string.max_weight_pris,
                String.format(Locale.ENGLISH, "%.3f", prisyadResult.doubleValue)
            ),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = prisyad.value,
            label = { Text(text = "Вес") },
            onValueChange = { prisyad.value = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                coroutine.launch {
                    withContext(Dispatchers.Default) {
                        prisyadResult.doubleValue = prisyad.value.toDouble() * (1 + 8 / 30.0)
                    }
                }
            }),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
        )
    }
}