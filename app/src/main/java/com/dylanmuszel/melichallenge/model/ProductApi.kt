package com.dylanmuszel.melichallenge.model

import com.google.gson.annotations.SerializedName

data class ProductApi(
    val id: String,
    val title: String,
    val price: Float,
    @SerializedName("available_quantity")
    val availableQuantity: Int,
    @SerializedName("sold_quantity")
    val soldQuantity: Int,
    val seller: SellerApi,
    @SerializedName("list_type_id")
    val listingTypeId: String,
    val condition: String,
    val permalink: String,
    val thumbnail: String,
//    val address: ProductAddressApi,
//    val shipping: ShippingApi,
//    val attributes: List<ProductAttributeApi>,
    @SerializedName("category_id")
    val categoryId: Int,
    val tags: List<String>
)