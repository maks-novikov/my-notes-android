package com.maksim.mynotes.domain.note

import com.maksim.mynotes.data.api.notes.NoteResponse
import com.maksim.mynotes.data.model.NoteEntity

class NoteMapper {

    fun responseToNote(response: NoteResponse): Note {
        return with(response) {
            Note(id, title, description, createdAt)
        }
    }

    fun responseToNote(responses: List<NoteResponse>): List<Note> {
        return responses.map { responseToNote(it) }
    }

    fun responseToEntity(response: NoteResponse): NoteEntity {
        return with(response) {
            NoteEntity(id, title, description, createdAt)
        }
    }

    fun entityToNote(entity: NoteEntity): Note {
        return with(entity) {
            Note(id, title, description, createdAt)
        }
    }
}