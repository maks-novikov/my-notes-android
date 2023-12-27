package com.maksim.mynotes.ui.base

import com.maksim.mynotes.ui.di.AppContainer

interface BaseView {

    fun getAppContainer(): AppContainer
    fun closeKeyboard()
}