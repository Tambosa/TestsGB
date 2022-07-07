package com.geekbrains.tests

import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.view.details.ViewDetailsContract
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailsPresenterTest {
    private lateinit var presenter: DetailsPresenter

    @Mock
    private lateinit var viewContract: ViewDetailsContract

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = DetailsPresenter()
        presenter.onAttach(viewContract)
    }

    @Test
    fun onIncrement_Attached_Test() {
        presenter.onIncrement()
        Mockito.verify(viewContract, Mockito.times(1)).setCount(1)
    }

    @Test
    fun onMultipleIncrement_Attached_Test() {
        presenter.onIncrement()
        presenter.onIncrement()
        presenter.onIncrement()
        Mockito.verify(viewContract, Mockito.times(3)).setCount(anyInt())
    }

    @Test
    fun onDecrement_Attached_Test() {
        presenter.onDecrement()
        Mockito.verify(viewContract, Mockito.times(1)).setCount(-1)
    }

    @Test
    fun onMultipleDecrement_Attached_Test() {
        presenter.onDecrement()
        presenter.onDecrement()
        presenter.onDecrement()
        Mockito.verify(viewContract, Mockito.times(3)).setCount(anyInt())
    }

    @Test
    fun onAny_Detached_Test() {
        presenter.onDetach()
        presenter.onIncrement()
        presenter.onDecrement()
        Mockito.verify(viewContract, Mockito.times(0)).setCount(anyInt())
    }
}