package com.example.apppoli.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AppDarkColorScheme = darkColorScheme(
    primary = YellowPoli,
    onPrimary = Color.Black,
    background = BlackBackground,
    surface = DarkGreySurface,
    onSurface = Color.White,
    surfaceVariant = MediumGreySurfaceVariant,
    onSurfaceVariant = Color.White
)

@Composable
fun AppPoliTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppDarkColorScheme,
        typography = Typography,
        content = content
    )
}
