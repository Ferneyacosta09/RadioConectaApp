package com.example.apppoli.ui.components

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.FullscreenExit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.apppoli.data.model.VideoItem

// Función de utilidad para encontrar la actividad
fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@OptIn(UnstableApi::class)
@Composable
fun CardTop(video: VideoItem, isHero: Boolean = false) {
    val context = LocalContext.current
    val goldColor = Color(0xFFFFD600)
    var isFullScreen by remember { mutableStateOf(false) }

    // Creamos el ExoPlayer y lo recordamos basándonos en el ranking
    val exoPlayer = remember(video.ranking) {
        ExoPlayer.Builder(context).build().apply {
            val uri = if (video.imagenRes != 0) {
                Uri.parse("android.resource://${context.packageName}/${video.imagenRes}")
            } else if (video.videoUrl.isNotEmpty()) {
                Uri.parse(video.videoUrl)
            } else null

            uri?.let {
                setMediaItem(MediaItem.fromUri(it))
                prepare()
            }
            
            playWhenReady = false
            repeatMode = Player.REPEAT_MODE_OFF
        }
    }

    // Liberar el reproductor
    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }

    // Lógica de Pantalla Completa
    if (isFullScreen) {
        val activity = context.findActivity()
        DisposableEffect(activity) {
            activity?.window?.let { window ->
                val controller = WindowCompat.getInsetsController(window, window.decorView)
                controller.hide(WindowInsetsCompat.Type.systemBars())
                controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
            onDispose {
                activity?.window?.let { window ->
                    val controller = WindowCompat.getInsetsController(window, window.decorView)
                    controller.show(WindowInsetsCompat.Type.systemBars())
                }
            }
        }

        Dialog(
            onDismissRequest = { 
                isFullScreen = false
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnBackPress = true,
                dismissOnClickOutside = false
            )
        ) {
            Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
                AndroidView(
                    factory = { ctx ->
                        PlayerView(ctx).apply {
                            player = exoPlayer
                            useController = true
                            resizeMode = androidx.media3.ui.AspectRatioFrameLayout.RESIZE_MODE_FIT
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
                
                IconButton(
                    onClick = { 
                        isFullScreen = false
                        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    },
                    modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FullscreenExit, 
                        contentDescription = "Salir pantalla completa", 
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }

    // Diseño de la Tarjeta
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = if (isHero) 16.dp else 8.dp)
    ) {
        // Etiqueta de Ranking
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                color = if (isHero) goldColor else Color(0xFF252525),
                shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
                modifier = Modifier.padding(start = 4.dp)
            ) {
                Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                    if (isHero) Icon(Icons.Default.Star, null, tint = Color.Black, modifier = Modifier.size(14.dp))
                    Text(
                        text = "TOP ${video.ranking}",
                        color = if (isHero) Color.Black else Color.White,
                        fontWeight = FontWeight.Black,
                        fontSize = 12.sp
                    )
                }
            }
        }

        // Contenedor del Reproductor con controles NATIVOS
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (isHero) 240.dp else 180.dp)
                .shadow(if (isHero) 20.dp else 0.dp, spotColor = goldColor)
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp, topEnd = 16.dp))
                .background(Color.Black)
                .border(
                    width = if (isHero) 2.dp else 1.dp,
                    brush = Brush.verticalGradient(listOf(if (isHero) goldColor else Color.Gray.copy(alpha = 0.3f), Color.Transparent)),
                    shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp, topEnd = 16.dp)
                )
        ) {
            AndroidView(
                factory = { ctx ->
                    PlayerView(ctx).apply {
                        player = exoPlayer
                        useController = true // CONTROLADOR NATIVO ACTIVADO
                        setShowNextButton(false)
                        setShowPreviousButton(false)
                        controllerAutoShow = true
                        controllerShowTimeoutMs = 3000
                        resizeMode = androidx.media3.ui.AspectRatioFrameLayout.RESIZE_MODE_FIT
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
            
            if (!isFullScreen) {
                IconButton(
                    onClick = { 
                        isFullScreen = true
                        context.findActivity()?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Fullscreen, 
                        contentDescription = "Pantalla completa", 
                        tint = Color.White
                    )
                }
            }
        }

        // Información del Video
        Text(
            text = video.titulo,
            color = Color.White,
            fontSize = if (isHero) 18.sp else 16.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(top = 12.dp, start = 4.dp)
        )
    }
}
