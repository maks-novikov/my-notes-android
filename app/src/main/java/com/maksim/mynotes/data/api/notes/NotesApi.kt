package com.maksim.mynotes.data.api.notes

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface NotesApi {


    @GET("card")
    suspend fun getAllNotes(): List<NoteResponse>

    @GET("card/{id}")
    suspend fun getNote(id: Int): NoteResponse

    @POST("card/create")
    suspend fun createNote(@Body request: CreateNoteRequest): Unit

    @DELETE("card/{id}")
    suspend fun deleteNote(id: Int): Unit

    @PATCH("card")
    suspend fun updateNote(@Body request: UpdateCardRequest): Unit
}