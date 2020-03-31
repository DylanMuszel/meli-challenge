package com.dylanmuszel.melichallenge.model

import com.google.gson.annotations.SerializedName

data class SellerApi(
    val id: Long,
    @SerializedName("power_seller_status")
    val powerSellerStatus: String,
    val tags: List<String>
)

