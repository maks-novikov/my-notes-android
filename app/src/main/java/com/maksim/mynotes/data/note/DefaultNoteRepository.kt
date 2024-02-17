package com.maksim.mynotes.data.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.room.Database
import com.maksim.mynotes.data.api.notes.CreateNoteRequest
import com.maksim.mynotes.data.api.notes.NotesService
import com.maksim.mynotes.data.db.MyNotesDb
import com.maksim.mynotes.data.db.NoteDao
import com.maksim.mynotes.domain.AsyncResult
import com.maksim.mynotes.domain.note.Note
import com.maksim.mynotes.domain.note.NoteMapper
import com.maksim.mynotes.domain.note.NoteRepository
import java.lang.IllegalStateException

class DefaultNoteRepository(
    private val notesService: NotesService,
    private val noteDao: NoteDao,
    private val mapper: NoteMapper,
    private var syncing: Boolean = false
) : NoteRepository {
    override suspend fun getNotes(): AsyncResult<List<Note>> {
        return when (val response = notesService.getAllNotes()) {
            is AsyncResult.Data -> AsyncResult.success(mapper.responseToNote(response.data))
            is AsyncResult.Error -> AsyncResult.error(response.error)
            else -> throw IllegalStateException("")
        }
    }

    override fun setSyncing(isSyncing: Boolean) {
        syncing = isSyncing
    }

    override fun isSyncing(): Boolean {
        return syncing
    }

    override suspend fun getLocalNotes(): List<Note> {
        return noteDao.getAll().let { mapper.entityToNote(it) }
    }

    override fun observeAllNotes(): LiveData<List<Note>> {
        return noteDao.observeAll().map { noteEntities -> mapper.entityToNote(noteEntities) }
    }

    override suspend fun getNote(id: Long): AsyncResult<Note> {
        return when (val response = notesService.getNote(id)) {
            is AsyncResult.Data -> AsyncResult.success(mapper.responseToNote(response.data))
            is AsyncResult.Error -> AsyncResult.error(response.error)
            else -> throw IllegalStateException("")
        }
    }

    override suspend fun createNote(noteRequest: CreateNoteRequest): AsyncResult<Long> {

        val response = notesService.createNote(noteRequest)
        return if (response is AsyncResult.Data) {
            val id = noteDao.create(mapper.responseToEntity(response.data))
            AsyncResult.success(id)
        } else {
            response as AsyncResult.Error
            AsyncResult.error(response.error)
        }
    }

    override suspend fun createLocal(note: Note) {
        noteDao.create(mapper.noteToEntity(note))
    }

    override suspend fun updateNote(note: Note) {
        val response = notesService.updateNote(note)
        updateLocal(note)
    }

    override suspend fun updateLocal(note: Note) {
        noteDao.update(mapper.noteToEntity(note))
    }

    override fun observeNote(id: Long): LiveData<Note?> {
        return noteDao.observe(id).map {
            if (it != null) mapper.entityToNote(it) else null
        }
    }

    override suspend fun deleteNote(id: Long): AsyncResult<Unit> {
        noteDao.delete(id)
        return notesService.deleteNote(id)
    }

    override suspend fun deleteLocal(id: Long) {
        noteDao.delete(id)
    }

    override suspend fun clearAll() {
        noteDao.deleteAll()
    }
}