package com.dylanmuszel.domain

import com.sun.org.apache.xpath.internal.operations.Bool

data class Shipping(
    val freeShipping: Boolean,
    val mode: String,
    val tags: List<String>,
    val logisticType: String,
    val storePickUp: Bool
)
