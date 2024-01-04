package com.maksim.mynotes.ui.editNote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.maksim.mynotes.databinding.FragmentEditNoteBinding

data class EditNoteFragmentArgs(
    val noteId: Int? = null
)

class EditNoteFragment : Fragment() {

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

    private fun saveNote() {
        val title = binding.editNoteTitle.text.toString().trim()
        val description = binding.noteInput.text.toString().trim()

        viewModel.saveNote(title, description)
    }

    override fun onDestroyView() {
        saveNote()
        super.onDestroyView()
        _binding = null
    }
}