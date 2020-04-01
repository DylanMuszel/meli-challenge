package com.dylanmuszel.melichallenge.presentation.productdetail

import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.dylanmuszel.melichallenge.R
import com.dylanmuszel.melichallenge.presentation.mockProductUI
import com.dylanmuszel.melichallenge.presentation.model.ProductAttributeUI
import com.dylanmuszel.melichallenge.presentation.model.ProductUI
import com.dylanmuszel.melichallenge.presentation.model.SellerStatusUI
import com.dylanmuszel.melichallenge.presentation.resources
import com.dylanmuszel.melichallenge.presentation.targetContext
import com.dylanmuszel.melichallenge.presentation.withTextColor
import org.hamcrest.Matchers.not
import org.junit.Test

class ProductDetailFragmentTest {

    private lateinit var scenario: FragmentScenario<ProductDetailFragment>

    private fun setup(product: ProductUI) {
        scenario = launchFragmentInContainer<ProductDetailFragment>(
            bundleOf(ProductDetailFragment.PRODUCT_EXTRA to product),
            R.style.AppTheme
        )
    }

    @Test
    fun givenProductWhenCreateViewThenProductIsShown() {

        // GIVEN
        val product = mockProductUI()

        // WHEN
        setup(product)

        // THEN
        with(product) {
            onView(withId(R.id.condition_text)).check(matches(withText(conditionRes)))
            onView(withId(R.id.title_text)).check(matches(withText(title)))
            onView(withId(R.id.price_text)).check(matches(withText(price)))
            onView(withId(R.id.available_quantity_text))
                .check(matches(withText(resources.getString(availableQuantityTextRes, availableQuantity))))
            onView(withId(R.id.sold_quantity_text))
                .check(matches(withText(resources.getString(soldQuantityTextRes, soldQuantity))))
            onView(withId(R.id.seller_location_text)).check(matches(withText(address)))
            onView(withId(R.id.seller_status_text))
                .check(matches(withText(sellerStatus!!.textRes)))
                .check(matches(withTextColor(ContextCompat.getColor(targetContext, sellerStatus!!.colorRes))))
        }
    }

    @Test
    fun givenProductWithoutSellerStatusWhenCreateViewThenSellerStatusIsInvisible() {

        // GIVEN
        val product = mockProductUI(sellerStatus = null)

        // WHEN
        setup(product)

        // THEN
        onView(withId(R.id.seller_status_text)).check(matches(not(isDisplayed())))
        onView(withId(R.id.seller_status_icon)).check(matches(not(isDisplayed())))
    }

    @Test
    fun givenProductWithSellerStatusWhenCreateViewThenSellerStatusIsVisible() {

        // GIVEN
        val product = mockProductUI(sellerStatus = SellerStatusUI.PLATINUM)

        // WHEN
        setup(product)

        // THEN
        onView(withId(R.id.seller_status_text)).check(matches(isDisplayed()))
        onView(withId(R.id.seller_status_icon)).check(matches(isDisplayed()))
    }

    @Test
    fun givenProductWithoutAttributesWhenCreateViewThenAttributesIsInvisible() {

        // GIVEN
        val product = mockProductUI(attributes = emptyList())

        // WHEN
        setup(product)

        // THEN
        onView(withId(R.id.attributes_title)).check(matches(not(isDisplayed())))
        onView(withId(R.id.attributes_grid)).check(matches(not(isDisplayed())))
    }

    @Test
    fun givenProductWithAttributesWhenCreateViewThenAttributesIsVisible() {

        // GIVEN
        val product = mockProductUI(attributes = listOf(ProductAttributeUI("name", "value")))

        // WHEN
        setup(product)

        // THEN
        onView(withId(R.id.attributes_title)).check(matches(isDisplayed()))
        onView(withId(R.id.attributes_grid)).check(matches(isDisplayed()))
    }
}
