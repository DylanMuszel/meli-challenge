package com.dylanmuszel.usecases.product

import com.dylanmuszel.data.ProductRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Use case to search products by a query.
 */
class SearchProductsUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend operator fun invoke(query: String) = withContext(Dispatchers.IO) {
        repository.search(query)
    }
}
