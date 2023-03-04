package com.example.navigationrubenhita.ui.unGrupo

import com.example.navigationrubenhita.domain.modelo.Grupo

sealed class PartidoDeUnGrupoEvent {
    class SetGrupo(val id: Int) : PartidoDeUnGrupoEvent()
    class DeleteEquipo(val i: Int) : PartidoDeUnGrupoEvent()
    object SetGrupoGanadores : PartidoDeUnGrupoEvent()
}