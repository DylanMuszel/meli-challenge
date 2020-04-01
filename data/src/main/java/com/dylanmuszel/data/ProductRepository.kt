package com.dylanmuszel.data

import javax.inject.Inject

/**
 * Product repository that allows access to the products.
 */
class ProductRepository @Inject constructor(private val dataSource: ProductDataSource) {

    /** Search products by a [query]. */
    suspend fun search(query: String) = dataSource.search(query)
}
