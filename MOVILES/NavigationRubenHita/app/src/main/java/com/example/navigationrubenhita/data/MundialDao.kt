package com.example.navigationrubenhita.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.navigationrubenhita.data.modelo.EquipoEntity
import com.example.navigationrubenhita.data.modelo.GrupoEntity
import com.example.navigationrubenhita.data.modelo.GrupoWithEquipos

@Dao
interface MundialDao {
    @Query("SELECT * FROM equipos")
    suspend fun allEquipos(): List<EquipoEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertListaGrupos(gruposList: List<GrupoEntity>)

    @Query("SELECT * FROM grupos WHERE idGrupo=:id")
    suspend fun getUnGrupo(id: Int): GrupoWithEquipos

    @Delete
    suspend fun deleteGrupo(grupo: GrupoEntity)

    @Query("SELECT * FROM grupos")
    suspend fun getAllGrupos(): List<GrupoWithEquipos>

    @Delete
    suspend fun deleteEquipo(equipoEntity: EquipoEntity)

    @Update
    suspend fun updateGrupo(grupo: GrupoEntity)
}