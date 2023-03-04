package com.example.roomconrelaciones.data

object Constantes {
    const val SELECT_EQUIPOS = "SELECT * from equipos"
    const val DELETE_EQUIPOS = "DELETE FROM equipos WHERE id = :id"
    const val SELECT_JUGADORES = "SELECT * FROM jugadores WHERE equipoid = :id"
    const val DELETE_JUGADORES_DE_UN_EQUIPO = "DELETE FROM jugadores where equipoid = :idEquipo"
    const val DELETE_UN_JUGADOR = "DELETE FROM jugadores where nombre = :nombre"
    const val UPDATE_EQUIPO = "UPDATE equipos SET nombre = :nombre, estadio = :estadio WHERE id = :id"

    const val ASSETDB ="assetDB"
    const val RUTA_DB ="database/equipos.db"
    const val ITEMS = "items"

    const val JUGADORES = "jugadores"
    const val ID = "id"
    const val EQUIPO_ID = "equipoid"
    const val NOMBRE = "nombre"
    const val POSICION = "posicion"
    const val TITULOS = "titulos"
    const val EQUIPOS = "equipos"
    const val ESTADIO = "estadio"
}