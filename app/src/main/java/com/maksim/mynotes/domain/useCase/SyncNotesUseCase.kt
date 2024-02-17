package com.maksim.mynotes.domain.useCase

import android.util.Log
import com.maksim.mynotes.domain.AsyncResult
import com.maksim.mynotes.domain.note.Note
import com.maksim.mynotes.domain.note.NoteRepository

class SyncNotesUseCase(private val noteRepository: NoteRepository) {

    private val TAG = SyncNotesUseCase::class.java.simpleName
    suspend fun execute(): AsyncResult<Unit> {
        if (noteRepository.isSyncing())
            return AsyncResult.error(Throwable("PROCESS_BUSY"))

        noteRepository.setSyncing(true)
        when (val remoteResponse = noteRepository.getNotes()) {
            is AsyncResult.Data -> {
                Log.d(TAG, "Diff notes start")
                val localNotes = noteRepository.getLocalNotes()
                diffNotes(remoteResponse.data, localNotes)
                Log.d(TAG, "Diff notes end")
            }

            is AsyncResult.Error -> {
                Log.d(TAG, "error getting remote notes: ${remoteResponse.error.message}")
            }
        }
        noteRepository.setSyncing(false)
        return AsyncResult.success(Unit)
    }

    private suspend fun diffNotes(remoteNotes: List<Note>, localNotes: List<Note>) {
        val remoteNotesMap = mutableMapOf<Long, Note>()
        remoteNotes.forEach { remoteNotesMap[it.id] = it }
        val localNotesMap = mutableMapOf<Long, Note>()
        localNotes.forEach { localNotesMap[it.id] = it }

        localNotes.forEach { localNote ->
            when (val remoteNote = remoteNotesMap[localNote.id]) {
                //Note not exist in remote, delete from local
                null -> {
                    Log.d(TAG, "deleting local: ${localNote.id}")
                    noteRepository.deleteLocal(localNote.id)
                }

                else -> {
                    if (remoteNote != localNote) {
                        Log.d(TAG, "updating from remote: ${remoteNote.id}")
                        noteRepository.updateLocal(remoteNote)
                    }
                }
            }
        }

        remoteNotes.forEach { remoteNote ->
            when (localNotesMap[remoteNote.id]) {
                null -> {
                    Log.d(TAG, "creating from remote: ${remoteNote.id}")
                    noteRepository.createLocal(remoteNote)
                }
            }
        }
    }
}