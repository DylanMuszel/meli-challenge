package com.dylanmuszel.melichallenge.presentation.search

import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test

class SearchPresenterTest {

    private lateinit var searchView: SearchView
    private lateinit var searchPresenter: SearchPresenter

    @Before
    fun setup() {
        searchView = mock()
        searchPresenter = SearchPresenter().apply {
            onCreate(searchView)
        }
    }

    @Test
    fun `given a non empty query when search button is clicked then go to product list`() {

        // GIVEN
        val query = "query"

        // WHEN
        searchPresenter.onSearchButtonClicked(query)

        // THEN
        verify(searchView, only()).goToProductList(eq(query))
    }

    @Test
    fun `given an empty query when search button is clicked then show error`() {

        // GIVEN
        val query = "      "

        // WHEN
        searchPresenter.onSearchButtonClicked(query)

        // THEN
        verify(searchView, only()).showSearchEmptyError()
    }
}