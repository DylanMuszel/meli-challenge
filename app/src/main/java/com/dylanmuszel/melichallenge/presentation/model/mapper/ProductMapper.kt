package com.dylanmuszel.melichallenge.presentation.model.mapper

import com.dylanmuszel.domain.Product
import com.dylanmuszel.melichallenge.presentation.model.SellerStatusUI
import com.dylanmuszel.melichallenge.R
import com.dylanmuszel.melichallenge.presentation.model.ProductUI
import java.text.NumberFormat

fun Product.toProductUI() = ProductUI(
    title = title,
    price = NumberFormat.getCurrencyInstance().format(price),
    thumbnail = thumbnail,
    condition = when (condition) {
        "new" -> R.string.product_new
        "used" -> R.string.product_used
        else -> throw IllegalStateException()
    },
    availableQuantity = availableQuantity,
    soldQuantity = soldQuantity,
    sellerStatus = when (seller.powerSellerStatus) {
        "gold" -> SellerStatusUI.GOLD
        "platinum" -> SellerStatusUI.PLATINUM
        else -> null
    },
    address = "${address.cityName}, ${address.stateName}"
)