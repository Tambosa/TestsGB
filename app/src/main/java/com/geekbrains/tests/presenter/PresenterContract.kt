package com.geekbrains.tests.presenter

import android.view.View

interface PresenterContract {
    fun onAttach(view: View)
    fun onDetach()
}
