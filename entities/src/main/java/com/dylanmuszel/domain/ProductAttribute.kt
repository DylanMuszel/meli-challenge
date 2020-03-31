package com.dylanmuszel.domain

data class ProductAttribute(
    val values: List<ProductAttributeValue>,
    val attributeGroupId: String,
    val attributeGroupName: String,
    val id: String,
    val valueId: String,
    val valueName: String,
    val name: String
)
