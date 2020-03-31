package com.dylanmuszel.domain

data class Product(
    val id: String,
    val title: String,
    val price: Float,
    val availableQuantity: Int,
    val soldQuantity: Int,
    val seller: Seller,
    val listingTypeId: String,
    val condition: String,
    val permalink: String,
    val thumbnail: String,
    val address: ProductAddress,
    val shipping: Shipping,
    val attributes: List<ProductAttribute>,
    val categoryId: Int,
    val tags: List<String>
)