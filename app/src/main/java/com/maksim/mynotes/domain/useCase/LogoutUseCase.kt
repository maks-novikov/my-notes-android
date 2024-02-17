package com.maksim.mynotes.domain.useCase

import com.maksim.mynotes.domain.AuthRepository
import com.maksim.mynotes.domain.note.NoteRepository

class LogoutUseCase(
    private val authRepository: AuthRepository,
    private val noteRepository: NoteRepository
) {

    suspend fun execute() {
        //TODO clear other data storages
        noteRepository.clearAll()
        authRepository.logout()
    }
}