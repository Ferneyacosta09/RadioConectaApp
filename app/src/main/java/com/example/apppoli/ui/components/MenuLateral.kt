package com.example.apppoli.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.apppoli.R

@Composable
fun MenuLateral(
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val menuItems = listOf(
        Triple("Inicio", "Perfil", Icons.Default.Home),
        Triple("Noticias", "Fotos", Icons.Default.List),
        Triple("Top 10", "Video", Icons.Default.Star),
        Triple("Web Site", "Web", Icons.Default.Public)
    )

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = 16.dp, horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo en la parte superior del menú
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_menu),
                contentDescription = "Logo Menu Radio",
                modifier = Modifier.fillMaxSize().padding(4.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        menuItems.forEach { (label, value, icon) ->
            val isSelected = selectedOption == value
            
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .padding(vertical = 4.dp)
                    .clickable { onOptionSelected(value) },
                shape = RoundedCornerShape(12.dp),
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                tonalElevation = if (isSelected) 8.dp else 0.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = if (isSelected) Color.Black else Color.White.copy(alpha = 0.7f),
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (isSelected) Color.Black else Color.White.copy(alpha = 0.9f),
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // BOTÓN EN VIVO ESTILO 3D / PREMIUM
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(bottom = 8.dp)
                .clickable { onOptionSelected("Botones") },
            shape = RoundedCornerShape(16.dp),
            color = if (selectedOption == "Botones") MaterialTheme.colorScheme.primary else Color(0xFF2A2A2A),
            tonalElevation = 12.dp,
            shadowElevation = 8.dp,
            border = BorderStroke(
                width = 1.5.dp, 
                color = if (selectedOption == "Botones") Color.Black.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.8f)
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.SettingsInputAntenna, 
                        contentDescription = null,
                        tint = if (selectedOption == "Botones") Color.Black else Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "En Vivo",
                        style = MaterialTheme.typography.titleMedium,
                        color = if (selectedOption == "Botones") Color.Black else Color.White,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
        
        Text(
            text = "v1.0.2",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
