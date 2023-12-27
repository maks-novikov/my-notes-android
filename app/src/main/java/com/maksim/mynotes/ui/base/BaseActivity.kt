package com.maksim.mynotes.ui.base

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.maksim.mynotes.ui.auth.register.RegisterFragmentViewModel
import com.maksim.mynotes.ui.di.AppContainer

abstract class BaseActivity : AppCompatActivity(), BaseView {

    override fun getAppContainer(): AppContainer {
        TODO("Not yet implemented")
    }

    override fun closeKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = currentFocus

        if (view == null) {
            view = View(this)
        }

        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}