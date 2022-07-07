package com.geekbrains.tests.presenter.search

import com.geekbrains.tests.presenter.PresenterContract

internal interface PresenterSearchContract<V> : PresenterContract<V> {
    fun searchGitHub(searchQuery: String)
}
