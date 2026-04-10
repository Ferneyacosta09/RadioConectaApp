package com.example.apppoli.data.repository

import com.example.apppoli.R
import com.example.apppoli.data.model.Noticia
import com.example.apppoli.data.model.VideoItem

object FakeData {
    val noticias = listOf(
        Noticia(
            titulo = "Billboard y Rolling Stone consagran a Juanes como el mayor referente del rock latino del siglo XXI",
            descripcion = "A sus 25 años de carrera solista, Juanes vuelve a ocupar los primeros lugares de las listas internacionales, pues Billboard y Rolling Stone lo destacan como el artista más influyente del rock latino del siglo XXI.",
            imagenRes = R.drawable.noticia_juanes
        ),
        Noticia(
            titulo = "Shakira vuelve a Colombia con estreno, colaboraciones y grandes razones para celebrar",
            descripcion = "Antes de los conciertos en Cali y Bogotá, la cantante estrenará un EP en exclusiva con Spotify, donde reversiona algunas de sus canciones más populares. Tendrá colaboraciones inesperadas con Ed Sheeran y Beéle.",
            imagenRes = R.drawable.noticia_shakira
        ),
        Noticia(
            titulo = "Con miles de firmas buscan sacar a Bad Bunny del Super Bowl 2026",
            descripcion = "Ciudadanos del movimiento Maga han iniciado una campaña en línea que ha recogido miles de firmantes. También tendrían la propuesta sobre el posible reemplazo.",
            imagenRes = R.drawable.noticia_badbunny
        ),
        Noticia(
            titulo = "Oído al consejo: Daddy Yankee recomienda a los hombres casarse con capitulaciones, aunque no sean famosos",
            descripcion = "El \"Big Boss\" está ahora más enfocado en sus empresas Cartel Records y Los Cangris, así como en la música, pues regresó con un disco titulado Lamento en Baile.",
            imagenRes = R.drawable.noticia_daddy
        ),
        Noticia(
            titulo = "Medellín Music Lab crece: anuncian más estudios, más artistas y una conexión global con la empresa de BTS",
            descripcion = "Este viernes se anunció la expansión del programa con más estudios públicos, nuevas rutas de formación y la apertura a todas las edades. La iniciativa cuenta con el respaldo de HYBE Latin America.",
            imagenRes = R.drawable.noticia_medellin
        )
    )

    val videos = listOf(
        VideoItem(1, "Provenza - Karol G", R.drawable.portada_radio),
        VideoItem(2, "Monaco - Bad Bunny", R.drawable.portada_radio),
        VideoItem(3, "Puntería - Shakira", R.drawable.portada_radio)
    )
}
