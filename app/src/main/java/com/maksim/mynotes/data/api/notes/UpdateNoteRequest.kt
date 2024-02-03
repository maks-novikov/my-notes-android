package com.maksim.mynotes.data.api.notes

data class UpdateCardRequest(
    val id: Long,
    val title: String,
    val description: String
)