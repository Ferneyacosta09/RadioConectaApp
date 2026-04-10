package com.example.apppoli.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.apppoli.ui.components.MenuLateral
import com.example.apppoli.ui.screens.perfil.PerfilScreen
import com.example.apppoli.ui.screens.fotos.FotosScreen
import com.example.apppoli.ui.screens.video.VideoScreen
import com.example.apppoli.ui.screens.web.WebScreen
import com.example.apppoli.ui.screens.botones.BotonesScreen

@Composable
fun MainScreen() {
    var selectedOption by remember { mutableStateOf("Perfil") }

    Row(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        // Lado izquierdo: Menú lateral (40% del ancho)
        MenuLateral(
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it },
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.4f)
        )

        // Lado derecho: Contenedor dinámico (60% del ancho)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.6f)
        )
        {
            // Header superior
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when(selectedOption) {
                        "Perfil" -> "Perfil"
                        "Fotos" -> "Noticias"
                        "Video" -> "Top 10"
                        "Web" -> "Página Web"
                        "Botones" -> "En Vivo"
                        else -> "App Poli"
                    },
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            // Contenedor dinámico (Sin padding para que las imágenes lleguen al borde)
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                when (selectedOption) {
                    "Perfil" -> PerfilScreen()
                    "Fotos" -> FotosScreen()
                    "Video" -> VideoScreen()
                    "Web" -> WebScreen()
                    "Botones" -> BotonesScreen()
                    else -> Text("Seleccione una opción", color = Color.White)
                }
            }
        }
    }
}
