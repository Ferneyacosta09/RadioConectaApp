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
            Text(
                text = "Top 10 - Lo más visto",
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(topVideos) { video ->
            CardTop(video)
        }
    }
}
