package com.dylanmuszel.data

import arrow.core.Either
import com.dylanmuszel.core.fp.Failure
import com.dylanmuszel.domain.Product

/**
 * Data source of the products.
 */
interface ProductDataSource {

    /** Search products by a [query]. */
    suspend fun search(query: String): Either<Failure, List<Product>>
}