package com.dylanmuszel.domain

data class Product(
    val id: String,
    val title: String,
    val price: Float,
    val availableQuantity: Int,
    val soldQuantity: Int,
    val seller: Seller,
    val condition: String,
    val permalink: String,
    val thumbnail: String,
    val address: ProductAddress,
    val attributes: List<ProductAttribute>,
    val categoryId: String,
    val tags: List<String>
)