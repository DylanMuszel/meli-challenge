package com.dylanmuszel.melichallenge.presentation

import android.content.Context
import android.content.res.Resources
import androidx.test.platform.app.InstrumentationRegistry
import com.dylanmuszel.melichallenge.R
import com.dylanmuszel.melichallenge.presentation.model.ProductAttributeUI
import com.dylanmuszel.melichallenge.presentation.model.ProductUI
import com.dylanmuszel.melichallenge.presentation.model.SellerStatusUI

val targetContext: Context get() = InstrumentationRegistry.getInstrumentation().targetContext

val resources: Resources get() = targetContext.resources

@Suppress("MaxLineLength", "MagicNumber")
fun mockProductUI(
    title: String = "Title of a product",
    price: String = "$ 1.400,00",
    thumbnail: String = "https://cdn.pocket-lint.com/r/s/1200x/assets/images/150401-tv-feature-harry-potter-image1-vpdnsqfrou.jpg",
    conditionRes: Int = R.string.product_new,
    availableQuantityTextRes: Int = R.string.product_detail_available_quantity_last,
    availableQuantity: Int = 1,
    soldQuantityTextRes: Int = R.string.product_detail_sold_quantity_more_than,
    soldQuantity: Int = 50,
    sellerStatus: SellerStatusUI? = SellerStatusUI.PLATINUM,
    address: String = "Palermo, Argentina",
    attributes: List<ProductAttributeUI> = listOf(ProductAttributeUI("attr", "value")),
    permalink: String = "http:://www.facebook.com"
) = ProductUI(
    title = title,
    price = price,
    thumbnail = thumbnail,
    conditionRes = conditionRes,
    availableQuantityTextRes = availableQuantityTextRes,
    availableQuantity = availableQuantity,
    soldQuantityTextRes = soldQuantityTextRes,
    soldQuantity = soldQuantity,
    sellerStatus = sellerStatus,
    address = address,
    attributes = attributes,
    permalink = permalink
)
