package com.maksim.mynotes.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.maksim.mynotes.ui.di.AppContainer

abstract class BaseFragment: Fragment(), BaseView {

    private lateinit var _activity: BaseActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _activity = activity as BaseActivity
    }

    override fun getAppContainer(): AppContainer {
        return _activity.getAppContainer()
    }

    override fun closeKeyboard() {
        _activity.closeKeyboard()
    }

}