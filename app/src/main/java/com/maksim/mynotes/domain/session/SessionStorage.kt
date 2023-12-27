package com.maksim.mynotes.domain.session

interface SessionStorage {

    fun setSession(session: UserSession)
    fun getSession(): UserSession?
    fun clearSession()
}