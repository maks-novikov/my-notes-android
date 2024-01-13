package com.maksim.mynotes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val owner: Int,
    val title: String,
    val description: String,
    val createdAt: String
)