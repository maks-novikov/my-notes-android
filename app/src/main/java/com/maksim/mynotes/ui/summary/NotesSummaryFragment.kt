package com.maksim.mynotes.ui.summary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.maksim.mynotes.R
import com.maksim.mynotes.databinding.FragmentNotesSummaryBinding
import com.maksim.mynotes.domain.note.Note
import com.maksim.mynotes.ui.RecyclerItemDecoration
import com.maksim.mynotes.ui.editNote.EditNoteFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesSummaryFragment : Fragment() {

    private val TAG = NotesSummaryFragment::class.java.simpleName

    private var _binding: FragmentNotesSummaryBinding? = null
    private val binding get() = _binding!!
    private val notesAdapter by lazy {
        NoteSummaryAdapter(noteClickListener)
    }

    private val viewModel by viewModels<NotesSummaryViewModel>()

    private val noteClickListener: (note: Note) -> Unit = {
        findNavController().navigate(R.id.nav_edit_note, bundleOf(EditNoteFragment.NOTE_ID to it.id))
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotesSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createNoteFab.setOnClickListener {
            findNavController().navigate(R.id.nav_edit_note)
        }

        binding.noteSummaryRv.adapter = notesAdapter
        binding.noteSummaryRv.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        binding.noteSummaryRv.addItemDecoration(RecyclerItemDecoration(2, 16))

        observeState()
    }

    private fun observeState() {
        viewModel.notesLiveData.observe(viewLifecycleOwner) {
            Log.d(TAG, "notes: $it")
            notesAdapter.submitList(it)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}