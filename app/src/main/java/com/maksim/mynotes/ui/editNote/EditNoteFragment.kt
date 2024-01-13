package com.maksim.mynotes.ui.editNote

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.maksim.mynotes.databinding.FragmentEditNoteBinding
import dagger.hilt.android.AndroidEntryPoint

/*data class EditNoteFragmentArgs(
    val noteId: Int? = null
) {
    fun asBundle(): Bundle {
        return bundleOf("noteId" to noteId)
    }
}*/



@AndroidEntryPoint
class EditNoteFragment : Fragment() {

    companion object {
        const val NOTE_ID = "noteId"
    }

    private val viewModel by viewModels<EditNoteViewModel>()

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observeState()
    }

    private fun saveNote() {
        val title = binding.editNoteTitle.text.toString().trim()
        val description = binding.noteInput.text.toString().trim()

        viewModel.saveNote(title, description)
    }

    private fun observeState() {

        viewModel.noteLiveData.observe(viewLifecycleOwner) {
            Log.d("EditNoteFragmentOnNote", "Note -> ${it?.title}")
        }
    }

    /**
     * Call periodically when some data changed to save the note.
     */
    private fun onDataChanged() {
        saveNote()
    }

    override fun onDestroyView() {
        saveNote()
        super.onDestroyView()
        _binding = null
    }
}