package com.maksim.mynotes.data.api.notes

data class NoteResponse(
    val id: Long,
    val title: String,
    val description: String,
    val createdAt: String
)
