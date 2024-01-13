package com.maksim.mynotes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maksim.mynotes.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class MyNotesDb: RoomDatabase() {

    abstract fun noteDao(): NoteDao
}