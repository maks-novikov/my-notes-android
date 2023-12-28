package com.maksim.mynotes.domain.note

import com.maksim.mynotes.domain.AsyncResult

interface NoteRepository {
    suspend fun createNote(): AsyncResult<Unit>
    suspend fun deleteNote(id: Int): AsyncResult<Unit>
}