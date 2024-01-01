package com.maksim.mynotes.data.api

object BaseUrlProvider {

    fun getBaseUrl(): String {
        return "http://192.168.0.115:8080/api/v1/" //Home Windows PC
        //.baseUrl("http://192.168.40.31:8080/api/v1/") // Work MacBook
        //.baseUrl("http://localhost:8080/api/v1/")
    }
}