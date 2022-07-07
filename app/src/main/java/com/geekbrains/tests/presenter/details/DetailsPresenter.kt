package com.geekbrains.tests.presenter.details

import com.geekbrains.tests.view.details.ViewDetailsContract

internal class DetailsPresenter internal constructor(
    private var count: Int = 0
) : PresenterDetailsContract<ViewDetailsContract> {

    private var view: ViewDetailsContract? = null

    override fun onAttach(view: ViewDetailsContract) {
        this.view = view
    }

    override fun onDetach() {
        view = null
    }

    override fun setCounter(count: Int) {
        this.count = count
    }

    override fun onIncrement() {
        count++
        view?.setCount(count)
    }

    override fun onDecrement() {
        count--
        view?.setCount(count)
    }
}
