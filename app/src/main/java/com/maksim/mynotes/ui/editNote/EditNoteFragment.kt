package com.maksim.mynotes.ui.editNote

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.maksim.mynotes.R
import com.maksim.mynotes.databinding.FragmentEditNoteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditNoteFragment : Fragment() {

    companion object {
        const val NOTE_ID = "noteId"
    }

    private val viewModel by viewModels<EditNoteViewModel>()

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_note_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_delete -> {
                viewModel.deleteNote()
                true
            }

            else -> false
        }
    }

    private fun saveNote() {
        val title = binding.noteTitleInput.text.toString().trim()
        val description = binding.noteDescriptionInput.text.toString().trim()

        if(title.isNotEmpty() || description.isNotEmpty()) {
            showMessage("Saving...")
            viewModel.saveNote(title, description)
        }
    }


    private fun showMessage(message: String) {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeState() {

        viewModel.noteLiveData?.observe(viewLifecycleOwner) { note ->
            Log.d("EditNoteFragmentOnNote", "Note -> ${note?.title}")

            if (note != null) {
                binding.noteTitleInput.setText(note.title)
                binding.noteDescriptionInput.setText(note.description)
            } else {
                showMessage("Deleted...")
                binding.noteTitleInput.setText("")
                binding.noteDescriptionInput.setText("")
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        saveNote()
        super.onDestroyView()
        _binding = null
    }
}