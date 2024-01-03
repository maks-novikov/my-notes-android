package com.maksim.mynotes.domain.note

data class Note(
    val id: Int,
    val owner: Int,
    val title: String,
    val description: String,
    val createdAt: String
)
