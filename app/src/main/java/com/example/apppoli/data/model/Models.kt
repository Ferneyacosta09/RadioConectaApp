package com.example.apppoli.data.model

data class Noticia(
    val titulo: String,
    val descripcion: String,
    val imagenRes: Int
)

data class VideoItem(
    val ranking: Int,
    val titulo: String,
    val imagenRes: Int = 0,
    val videoUrl: String = ""
)
