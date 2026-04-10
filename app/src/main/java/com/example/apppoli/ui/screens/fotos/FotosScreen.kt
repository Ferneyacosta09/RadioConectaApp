package com.example.apppoli.ui.screens.fotos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.apppoli.data.repository.FakeData
import com.example.apppoli.ui.components.CardNoticia

@Composable
fun FotosScreen() {
    val noticias = FakeData.noticias

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Text(
                text = "Noticias",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(noticias) { noticia ->
            CardNoticia(noticia)
        }
    }
}
