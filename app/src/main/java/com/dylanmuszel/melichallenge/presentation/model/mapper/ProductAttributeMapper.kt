package com.dylanmuszel.melichallenge.presentation.model.mapper

import com.dylanmuszel.domain.ProductAttribute
import com.dylanmuszel.melichallenge.presentation.model.ProductAttributeUI

/** Mapper method that maps a [ProductAttribute] to a [ProductAttributeUI] that contains what UI needs. */
fun ProductAttribute.toProductAttributeUI() = ProductAttributeUI(name, values.joinToString { value -> value.name })