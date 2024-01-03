package com.maksim.mynotes.domain.note

import com.maksim.mynotes.data.api.notes.NoteResponse

class NoteMapper {

    fun responseToNote(response: NoteResponse): Note {
        return with(response) {
            Note(id, owner, title, description, createdAt)
        }
    }

    fun responseToNote(responses: List<NoteResponse>): List<Note> {
        return responses.map { responseToNote(it) }
    }
}