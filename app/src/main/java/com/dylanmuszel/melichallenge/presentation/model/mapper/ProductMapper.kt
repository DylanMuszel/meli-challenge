package com.dylanmuszel.melichallenge.presentation.model.mapper

import com.dylanmuszel.domain.Product
import com.dylanmuszel.melichallenge.presentation.model.ProductUI
import java.text.NumberFormat

fun Product.toProductUI() = ProductUI(
    title,
    NumberFormat.getCurrencyInstance().format(price),
    thumbnail
)