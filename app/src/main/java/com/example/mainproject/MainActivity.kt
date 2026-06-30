package com.example.mainproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GuessNumberGame()
                }
            }
        }
    }
}

@Composable
fun GuessNumberGame() {
    val secretHighlight by remember { mutableStateOf(Random.nextInt(0, 101)) }
    var textInput by remember { mutableStateOf("") }
    var labelHintText by remember { mutableStateOf("Попробуй угадать число от 0 до 100!") }
    var isNumberGuessed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isNumberGuessed) {
            AsyncImage(
                model = "https://img.championat.com/news/big/a/w/donk-iz-sostava-team-spirit-po-counter-strike-2-voshyol-v-spisok-30-do-30-ot-forbes_17165664592092305713.jpg",
                contentDescription = "Победа!",
                modifier = Modifier
                    .size(250.dp)
                    .padding(bottom = 16.dp)
            )
            Text(
                text = "Вы угадали! Это было число $secretHighlight",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )
        } else {
            Text(
                text = labelHintText,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = textInput,
            onValueChange = { newInput ->
                if (newInput.all { it.isDigit() }) {
                    textInput = newInput
                }
            },
            label = { Text("Введи число") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val userNumber = textInput.toIntOrNull()

                if (userNumber != null) {
                    if (userNumber == secretHighlight) {
                        isNumberGuessed = true
                    } else if (userNumber < secretHighlight) {
                        labelHintText = "Введенное число меньше загаданного"
                    } else {
                        labelHintText = "Введенное число больше загаданного"
                    }
                } else {
                    labelHintText = "Пожалуйста, введи корректное число!"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Проверить", fontSize = 16.sp)
        }
    }
}
