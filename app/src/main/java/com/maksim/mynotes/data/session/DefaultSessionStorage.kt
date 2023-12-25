package com.maksim.mynotes.data.session

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import com.maksim.mynotes.domain.session.SessionStorage
import com.maksim.mynotes.domain.session.UserSession

class DefaultSessionStorage(private val context: Context) : SessionStorage {

    private val prefs = context.getSharedPreferences("com.maksim.myNotes.session", MODE_PRIVATE)

    private val sessionKey = "user_session"
    private val gson = Gson()

    override fun setSession(session: UserSession) {
        val json = gson.toJson(session)
        prefs.edit().putString(sessionKey, json).apply()
    }

    override fun getSession(): UserSession? {
        val json = prefs.getString(sessionKey, null) ?: return null
        return gson.fromJson(json, UserSession::class.java)
    }

    override fun clearSession() {
        prefs.edit().clear().apply()
    }

}