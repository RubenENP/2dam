package com.example.roomrubenhita.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomrubenhita.data.modelo.FutbolistaEntity

@Dao
interface FutbolistaDao {
    @Query(ConstantesDB.SELECT_futbolistas)
    suspend fun getAll(): List<FutbolistaEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(futbolista: FutbolistaEntity)

    @Query(ConstantesDB.DELETE_FUTBOLISTAS)
    suspend fun delete(nombre: String)
}