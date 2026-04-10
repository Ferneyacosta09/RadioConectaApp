package com.example.apppoli.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apppoli.data.model.Noticia

@Composable
fun CardNoticia(noticia: Noticia) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { expanded = !expanded }
            .animateContentSize(), // Para que la expansión sea suave
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A1A)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            // Imagen de la noticia
            Image(
                painter = painterResource(id = noticia.imagenRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = noticia.titulo,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFFFFD600),
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp
                )
                
                // Solo se muestra el contenido extra si el usuario selecciona la noticia
                if (expanded) {
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    Text(
                        text = noticia.descripcion,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.LightGray,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}
