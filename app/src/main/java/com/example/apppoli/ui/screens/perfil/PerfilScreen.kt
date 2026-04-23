package com.example.apppoli.ui.screens.perfil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Headset
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apppoli.R

@Composable
fun PerfilScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F0F0F)) // Fondo muy oscuro
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // 1. SECCIÓN DE LOGO CON GLOW (RESPLANDOR)
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            // Efecto de resplandor dorado detrás del logo
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFFFD600).copy(alpha = 0.15f),
                                Color.Transparent
                            )
                        )
                    )
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Logo RC en cuadrado redondeado con borde dorado
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, Color(0xFFFFD600), RoundedCornerShape(12.dp))
                        .background(Color.Black, RoundedCornerShape(12.dp))
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_radio),
                        contentDescription = "Logo RC",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Radio Conecta",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "(Más que radio, somos tu compañía.)",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 2. CARDS DE INFORMACIÓN (Estilo exacto al diseño solicitado)
        
        InfoCard(
            icon = Icons.Default.Headset,
            title = "Sobre Radio Conecta",
            content = {
                Text(
                    text = "Somos una emisora digital que conecta música, cultura y emociones, llevando a tus oídos lo mejor del género urbano, los hits del momento y una programación en vivo pensada para acompañarte 24/7.",
                    color = Color.LightGray,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    textAlign = TextAlign.Justify
                )
            }
        )

        InfoCard(
            icon = Icons.Default.MusicNote,
            title = "Contenido",
            content = {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    ListItem("Top 5 semanal")
                    ListItem("Noticias musicales")
                    ListItem("Programación en vivo")
                }
            }
        )

        InfoCard(
            icon = Icons.Default.TrackChanges,
            title = "Objetivo",
            content = {
                Text(
                    text = "Brindar acceso a contenido multimedia y transmisión en vivo 24/7.",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            }
        )

        InfoCard(
            icon = Icons.Default.Radio,
            title = "Disponibilidad",
            content = {
                Text(
                    text = "Escúchanos en vivo en cualquier momento desde la app",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            }
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun InfoCard(icon: ImageVector, title: String, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A1A)), // Gris muy oscuro
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color(0xFFFFD600),
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    color = Color(0xFFFFD600),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Línea divisoria amarilla fina
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(Color(0xFFFFD600).copy(alpha = 0.5f))
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            content()
        }
    }
}

@Composable
fun ListItem(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = Color(0xFFFFD600),
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = Color.LightGray,
            fontSize = 14.sp
        )
    }
}
