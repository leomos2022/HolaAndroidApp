package com.example.holaandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.holaandroidapp.ui.theme.*

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HolaAndroidAppTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    var messageState by remember { mutableStateOf(0) }
    val messages = listOf(
        "¡Hola, Android!",
        "HolaAndroid,\n\nexploremos este increíble\nLenguaje de Programación",
        "😊 ¡Android Studio\nes lo Máximo!"
    )

    val backgroundColors = listOf(BackgroundColor1, BackgroundColor2, BackgroundColor3)
    val transition = rememberInfiniteTransition(label = "background")
    val colorTransition by transition.animateColor(
        initialValue = backgroundColors[0],
        targetValue = backgroundColors[2],
        animationSpec = infiniteRepeatable(
            animation = tween(3000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "color"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorTransition),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(24.dp)
        ) {
            AnimatedContent(
                targetState = messageState,
                transitionSpec = {
                    slideInVertically { height -> height } + fadeIn() with
                    slideOutVertically { height -> -height } + fadeOut()
                },
                label = "message"
            ) { state ->
                Text(
                    text = messages[state],
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    color = TextColor,
                    lineHeight = 36.sp,
                    modifier = Modifier.padding(bottom = 48.dp)
                )
            }
            
            Button(
                onClick = {
                    messageState = (messageState + 1) % messages.size
                },
                colors = ButtonDefaults.buttonColors(containerColor = ButtonPurple),
                modifier = Modifier
                    .padding(16.dp)
                    .animateContentSize()
            ) {
                Text(
                    "Presióname",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    HolaAndroidAppTheme {
        MainScreen()
    }
}