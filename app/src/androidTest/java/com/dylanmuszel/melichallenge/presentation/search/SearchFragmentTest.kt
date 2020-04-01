package com.dylanmuszel.melichallenge.presentation.search

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dylanmuszel.melichallenge.R
import com.dylanmuszel.melichallenge.presentation.resources
import org.junit.Before
import org.junit.Test

class SearchFragmentTest {

    private lateinit var scenario: FragmentScenario<SearchFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer<SearchFragment>()
    }

    @Test
    fun whenShowSearchEmptyErrorThenSearchInputHasError() {

        // GIVEN

        // WHEN
        scenario.onFragment { it.showSearchEmptyError() }

        // THEN
        onView(withId(R.id.search_input)).check(matches(hasErrorText(resources.getString(R.string.search_empty_error))))
    }
}
