package com.maksim.mynotes.ui.summary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maksim.mynotes.databinding.NoteSummaryItemBinding
import com.maksim.mynotes.domain.note.Note

class NoteSummaryAdapter (
    private val noteClickListener: (note: Note) -> Unit
): ListAdapter<Note, NoteSummaryAdapter.NoteViewHolder>(NoteDiffer()) {

    class NoteViewHolder(
        private val noteCell: NoteSummaryItemBinding
    ) : RecyclerView.ViewHolder(noteCell.root) {
        fun bind(note: Note) {
            noteCell.titleTv.text = note.title
            noteCell.descriptionTv.text = note.description

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val cell = NoteSummaryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoteViewHolder(cell)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
        holder.itemView.setOnClickListener { noteClickListener(note) }
    }

    class NoteDiffer : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.isSameNote(newItem)
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return !oldItem.isContentDifferent(newItem)
        }
    }

}