package com.example.apppoli.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Pause
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Reproductor(
    isPlaying: Boolean,
    onPlayPauseClick: () -> Unit
) {
    // Animación de pulso para el botón cuando está reproduciendo
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (isPlaying) 1.05f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    // Animación de opacidad para el punto rojo de "En Vivo"
    val liveDotAlpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "liveDotAlpha"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(24.dp)
    ) {
        // Botón/Indicador de "LIVE" mejorado
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .clickable { onPlayPauseClick() },
            color = if (isPlaying) Color.Red.copy(alpha = 0.15f) else Color.Gray.copy(alpha = 0.1f),
            border = BorderStroke(
                width = 1.dp,
                color = if (isPlaying) Color.Red else Color.Gray.copy(alpha = 0.5f)
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(
                            if (isPlaying) Color.Red.copy(alpha = liveDotAlpha) 
                            else Color.Gray
                        )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isPlaying) "ESCUCHANDO EN VIVO" else "RADIO OFFLINE",
                    style = MaterialTheme.typography.labelLarge,
                    color = if (isPlaying) Color.White else Color.Gray,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(60.dp))

        // Botón de reproducción principal (Aspecto de botón real)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.scale(scale)
        ) {
            // Sombra/Brillo exterior cuando está activo
            if (isPlaying) {
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                )
            }

            Surface(
                modifier = Modifier
                    .size(160.dp)
                    .clickable { onPlayPauseClick() },
                shape = CircleShape,
                color = if (isPlaying) MaterialTheme.colorScheme.primary else Color(0xFF1A1A1A),
                tonalElevation = 12.dp,
                shadowElevation = 16.dp,
                border = BorderStroke(4.dp, if (isPlaying) Color.Black.copy(alpha = 0.1f) else MaterialTheme.colorScheme.primary)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                        contentDescription = if (isPlaying) "Pausar" else "Reproducir",
                        modifier = Modifier.size(70.dp),
                        tint = if (isPlaying) Color.Black else MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(60.dp))
        
        Text(
            text = "RADIO CONECTA",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary,
            letterSpacing = 2.sp
        )

        Text(
            text = if (isPlaying) "Más que radio, somos tu compañía" else "Conéctate con nosotros",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.7f),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
