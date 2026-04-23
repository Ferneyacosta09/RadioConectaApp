package com.example.apppoli.ui.screens.botones

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.apppoli.ui.components.Reproductor

@Composable
fun BotonesScreen() {
    val context = LocalContext.current
    val streamUrl = "https://sonic.paulatina.co/8182/stream"
    
    // Inicializar ExoPlayer
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(streamUrl)
            setMediaItem(mediaItem)
            prepare()
        }
    }

    var isPlaying by remember { mutableStateOf(false) }

    // Controlar la reproducción
    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            exoPlayer.play()
        } else {
            exoPlayer.pause()
        }
    }

    // Liberar el reproductor cuando se salga de la pantalla
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

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
