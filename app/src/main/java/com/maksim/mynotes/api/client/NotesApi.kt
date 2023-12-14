package com.maksim.mynotes.api.client

import retrofit2.http.GET
import retrofit2.http.POST

interface NotesApi {


    @GET("card")
    suspend fun getAllNotes()

    @GET("card/{id}")
    suspend fun getNote(id: Int)

    @POST("card/create")
    suspend fun createNot()
}