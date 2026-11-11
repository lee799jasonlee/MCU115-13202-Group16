package com.example.myfirstapphomework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                GreetingText()
            }
        }
    }
}

@Composable
fun GreetingText() {
    // 使用 Box 將文字靠左垂直置中，並加上左側邊距
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp), // 與左側邊界間距
        contentAlignment = Alignment.CenterStart // 靠左垂直居中
    ) {
        Text(
            text = "Hello World !!!",
            fontSize = 32.sp,              // 字體變大
            fontWeight = FontWeight.Bold,   // 粗體
            color = Color(0xFF00E676)       // 亮綠色（符合題目截圖）
        )
    }
}