package com.dylanmuszel.melichallenge.framework.product

import com.dylanmuszel.domain.Product
import com.dylanmuszel.melichallenge.framework.core.network.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service representing the product api endpoints.
 */
interface ProductService {

    /** Searches products by a given [query] and returns a [NetworkResponse] containing a [Product] [List]. */
    @GET("/search")
    suspend fun search(@Query("q") query: String): NetworkResponse<List<Product>>
}