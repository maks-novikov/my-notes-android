package com.maksim.mynotes.ui.summary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksim.mynotes.domain.AsyncResult
import com.maksim.mynotes.domain.note.Note
import com.maksim.mynotes.domain.useCase.ObserveAllNotesUseCase
import com.maksim.mynotes.domain.useCase.SyncNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesSummaryViewModel @Inject constructor(
    private val observeAllNotesUseCase: ObserveAllNotesUseCase,
    private val syncNotesUseCase: SyncNotesUseCase
) : ViewModel() {

    val notesLiveData: LiveData<List<Note>> = observeAllNotesUseCase.execute()
    private val _generalErrorLiveData = MutableLiveData<String?>()
    val generalErrorLiveData: LiveData<String?> = _generalErrorLiveData

    fun syncNotes() {
        viewModelScope.launch {
            when (val result = syncNotesUseCase.execute()) {
                is AsyncResult.Data -> {
                    _generalErrorLiveData.postValue("Sync finished")
                }

                is AsyncResult.Error -> {
                    val errorMessage = if (result.error.message == "PROCESS_BUSY") {
                        "Already syncing..."
                    } else {
                        "Unknown error happened, please try again later."
                    }
                    _generalErrorLiveData.postValue(errorMessage)
                }

            }
        }
    }
}