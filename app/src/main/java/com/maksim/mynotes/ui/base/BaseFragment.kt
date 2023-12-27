package com.maksim.mynotes.ui.base

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment(), BaseView {

    private lateinit var _activity: BaseActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _activity = activity as BaseActivity
    }

    override fun closeKeyboard() {
        _activity.closeKeyboard()
    }

}