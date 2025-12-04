package com.example.gamestore

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SplashScreen {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}

@Composable
fun SplashScreen(onFinish: () -> Unit) {

    var startAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        startAnimation = true
        delay(2000)
        onFinish()
    }

    val logoScale by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.85f,
        label = "logoScale"
    )

    val logoAlpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        label = "logoAlpha"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A1A2F))
    ) {


        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ultrastore_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(260.dp)
                    .scale(logoScale)
                    .alpha(logoAlpha)
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 220.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "A carregar UltraStore",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(8.dp))

            LoadingDots()
        }
    }
}

@Composable
fun LoadingDots() {
    var dotCount by remember { mutableStateOf(1) }

    LaunchedEffect(Unit) {
        while (true) {
            dotCount = (dotCount % 3) + 1
            delay(400)
        }
    }

    Text(
        text = ".".repeat(dotCount),
        color = Color.White,
        fontSize = 22.sp
    )
}
