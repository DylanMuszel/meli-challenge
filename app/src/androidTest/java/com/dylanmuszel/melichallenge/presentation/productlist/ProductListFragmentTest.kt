package com.dylanmuszel.melichallenge.presentation.productlist

import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.dylanmuszel.melichallenge.R
import com.dylanmuszel.melichallenge.presentation.atPosition
import com.dylanmuszel.melichallenge.presentation.mockProductUI
import com.dylanmuszel.melichallenge.presentation.resources
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Test

class ProductListFragmentTest {

    private lateinit var scenario: FragmentScenario<ProductListFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer<ProductListFragment>(
            bundleOf(ProductListFragment.QUERY_EXTRA to "Search"),
            R.style.AppTheme
        )
    }

    @Test
    fun givenListOfProductsWhenShowProductsThenProductsAreAtTheRecycler() {

        // GIVEN
        val products = listOf(mockProductUI(), mockProductUI())

        // WHEN
        scenario.onFragment { it.showProducts(products) }

        // THEN
        onView(withId(R.id.product_list_recycler))
            .check(matches(
                atPosition(
                    0,
                    hasDescendant(withText(products[0].title))
                )
            ))
        onView(withId(R.id.product_list_recycler))
            .check(matches(
                atPosition(
                    0,
                    hasDescendant(withText(products[0].price))
                )
            ))
        onView(withId(R.id.product_list_recycler))
            .check(matches(
                atPosition(
                    1,
                    hasDescendant(withText(products[1].title))
                )
            ))
        onView(withId(R.id.product_list_recycler))
            .check(matches(
                atPosition(
                    1,
                    hasDescendant(withText(products[1].price))
                )
            ))
    }

    @Test
    fun givenVisibleLoadingWhenMakeInvisibleThenLoadingIsInvisible() {

        // GIVEN
        scenario.onFragment { it.requireView().findViewById<View>(R.id.loading).isVisible = true }

        // WHEN
        scenario.onFragment { it.toggleLoadingVisibility(false) }

        // THEN
        onView(withId(R.id.loading)).check(matches(not(isDisplayed())))
    }

    @Test
    fun givenInvisibleLoadingWhenMakeVisibleThenLoadingIsVisible() {

        // GIVEN
        scenario.onFragment { it.requireView().findViewById<View>(R.id.loading).isVisible = false }

        // WHEN
        scenario.onFragment { it.toggleLoadingVisibility(true) }

        // THEN
        onView(withId(R.id.loading)).check(matches(isDisplayed()))
    }

    @Test
    fun givenSearchQueryWhenSetTitleThenTitleIsTheSearch() {

        // GIVEN
        val query = "query"

        // WHEN
        scenario.onFragment { it.setSearchingQueryTitle(query) }

        // THEN
        val text = resources.getString(R.string.product_list_title, query)
        onView(withId(R.id.toolbar))
            .check(matches(hasDescendant(withText(text))))
    }

    @Test
    fun whenShowNetworkConnectionErrorThenErrorIsShown() {

        // GIVEN

        // WHEN
        scenario.onFragment { it.showNetworkConnectionError() }

        // THEN
        onView(withId(R.id.error_text))
            .check(matches(withText(R.string.network_error)))
    }

    @Test
    fun whenShowUnexpectedErrorThenErrorIsShown() {

        // GIVEN

        // WHEN
        scenario.onFragment { it.showUnexpectedError() }

        // THEN
        onView(withId(R.id.error_text))
            .check(matches(withText(R.string.unexpected_error)))
    }

    @Test
    fun whenShowNoProductsErrorThenErrorIsShown() {

        // GIVEN

        // WHEN
        scenario.onFragment { it.showNoProductsError() }

        // THEN
        onView(withId(R.id.error_text))
            .check(matches(withText(R.string.product_list_empty_error)))
    }
}
