package com.maksim.mynotes.data.api.notes

data class UpdateCardRequest(
    val id: Int,
    val title: String,
    val description: String,
    val createdAt: String
)