package com.dylanmuszel.melichallenge.presentation.model.mapper

import com.dylanmuszel.domain.Product
import com.dylanmuszel.melichallenge.presentation.model.SellerStatusUI
import com.dylanmuszel.melichallenge.R
import com.dylanmuszel.melichallenge.presentation.model.ProductAttributeUI
import com.dylanmuszel.melichallenge.presentation.model.ProductUI
import java.text.NumberFormat

/** Mapper method that maps a [Product] to a [ProductUI] that contains what UI needs. */
fun Product.toProductUI() = ProductUI(
    title = title,
    price = NumberFormat.getCurrencyInstance().format(price),
    thumbnail = thumbnail,
    conditionRes = when (condition) {
        "new" -> R.string.product_new
        "used" -> R.string.product_used
        else -> throw IllegalStateException()
    },
    availableQuantity = availableQuantity,
    availableQuantityTextRes = when {
        availableQuantity == 0 -> R.string.product_detail_available_quantity_zero
        availableQuantity == 1 -> R.string.product_detail_available_quantity_last
        availableQuantity <= 5 -> R.string.product_detail_available_quantity_number
        else -> R.string.product_detail_available_quantity_more_than
    },
    soldQuantity = soldQuantity,
    soldQuantityTextRes = when {
        soldQuantity == 0 -> R.string.product_detail_sold_quantity_zero
        soldQuantity == 1 -> R.string.product_detail_sold_quantity_one
        soldQuantity <= 5 -> R.string.product_detail_sold_quantity_plural
        else -> R.string.product_detail_sold_quantity_more_than
    },
    sellerStatus = when (seller.powerSellerStatus) {
        "gold" -> SellerStatusUI.GOLD
        "platinum" -> SellerStatusUI.PLATINUM
        else -> null
    },
    address = "${address.cityName}, ${address.stateName}",
    attributes = attributes.map { it.toProductAttributeUI() }
)