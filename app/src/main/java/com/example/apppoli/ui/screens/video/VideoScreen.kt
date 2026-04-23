package com.example.apppoli.ui.screens.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apppoli.data.repository.FakeData
import com.example.apppoli.ui.components.CardTop

@Composable
fun VideoScreen() {
    val topVideos = FakeData.videos

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F0F0F)),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Column(modifier = Modifier.padding(bottom = 24.dp)) {
                Text(
                    text = "RADIO CONECTA",
                    color = Color(0xFFFFD600),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Ranking Semanal Top 5",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Black
                )
                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .height(3.dp)
                        .padding(top = 4.dp)
                        .background(Color(0xFFFFD600))
                )
            }
        }
        
        items(topVideos) { video ->
            CardTop(
                video = video,
                isHero = true // Todos los videos ahora tienen el diseño Hero (como el Top 1)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
