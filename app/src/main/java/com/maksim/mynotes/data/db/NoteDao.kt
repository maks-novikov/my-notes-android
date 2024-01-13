package com.maksim.mynotes.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.maksim.mynotes.data.model.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteEntity")
    suspend fun getAll(): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(noteEntity: NoteEntity)

    @Update
    suspend fun update(noteEntity: NoteEntity)

    @Delete
    suspend fun delete(entity: NoteEntity)

    @Query("DELETE FROM NoteEntity WHERE id = :id")
    suspend fun delete(id: Int)
}