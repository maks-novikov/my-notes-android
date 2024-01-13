package com.maksim.mynotes.domain.note

data class Note(
    val id: Int,
    val owner: Int,
    val title: String,
    val description: String,
    val createdAt: String
){

    fun isContentDifferent(note: Note): Boolean{
        return  note.title != title || note.description != description
    }

    fun isSameNote(note: Note): Boolean {
        return note.id == id && note.owner == owner
    }
}
