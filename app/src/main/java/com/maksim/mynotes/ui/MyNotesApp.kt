package com.maksim.mynotes.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyNotesApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}