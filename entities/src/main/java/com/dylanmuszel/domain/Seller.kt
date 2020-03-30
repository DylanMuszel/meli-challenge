package com.dylanmuszel.domain

data class Seller(
    val id: Long,
    val powerSellerStatus: String,
    val carDealer: Boolean,
    val realEstateAgency: Boolean,
    val tags: List<String>
)
