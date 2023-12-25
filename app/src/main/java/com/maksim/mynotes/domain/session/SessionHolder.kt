package com.maksim.mynotes.domain.session

import java.util.concurrent.atomic.AtomicReference

class SessionHolder(private val sessionStorage: SessionStorage) {

    private var session = AtomicReference<UserSession?>()

    init {
        sessionStorage.getSession()?.let { setSession(it) }
    }

    fun getSession(): UserSession? = session.get()

    fun setSession(session: UserSession) {
        this.session.set(session)
    }

    fun clearSession() {
        this.session.set(null)
        sessionStorage.clearSession()
    }

}