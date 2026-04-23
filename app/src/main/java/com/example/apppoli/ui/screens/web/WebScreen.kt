package com.example.apppoli.ui.screens.web

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebScreen() {
    var urlInput by remember { mutableStateOf("https://radioconecta.netlify.app/") }
    var webView: WebView? by remember { mutableStateOf(null) }
    val goldColor = Color(0xFFFFD600)

    Column(modifier = Modifier.fillMaxSize()) {
        // BARRA DE URL EXTENSA
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color(0xFF1A1A1A), RoundedCornerShape(8.dp))
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = urlInput,
                onValueChange = { urlInput = it },
                modifier = Modifier.weight(1f).height(50.dp),
                placeholder = { Text("Escribe una URL...", fontSize = 12.sp, color = Color.Gray) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Go, keyboardType = KeyboardType.Uri),
                keyboardActions = KeyboardActions(onGo = {
                    val targetUrl = if (urlInput.startsWith("http")) urlInput else "https://$urlInput"
                    webView?.loadUrl(targetUrl)
                }),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = LocalTextStyle.current.copy(fontSize = 13.sp),
                trailingIcon = {
                    if (urlInput.isNotEmpty()) {
                        IconButton(onClick = { urlInput = "" }) {
                            Icon(Icons.Default.Close, null, tint = Color.Gray, modifier = Modifier.size(18.dp))
                        }
                    }
                }
            )

            Button(
                onClick = {
                    val targetUrl = if (urlInput.startsWith("http")) urlInput else "https://$urlInput"
                    webView?.loadUrl(targetUrl)
                },
                colors = ButtonDefaults.buttonColors(containerColor = goldColor),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.height(42.dp).padding(horizontal = 4.dp)
            ) {
                Text("IR", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
        }

        // NAVEGADOR (OCUPA SOLO EL ESPACIO DERECHO)
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    setBackgroundColor(0x00000000) // Transparente para evitar el flash inicial
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    webViewClient = WebViewClient()
                    loadUrl(urlInput)
                    webView = this
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
