package com.example.apppoli.ui.screens.botones

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.apppoli.ui.components.Reproductor

@Composable
fun BotonesScreen() {
    var isPlaying by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Reproductor(
            isPlaying = isPlaying,
            onPlayPauseClick = { isPlaying = !isPlaying }
        )
    }
}
