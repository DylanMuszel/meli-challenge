package com.dylanmuszel.usecases.product

import com.dylanmuszel.core.fp.FeatureFailure
import com.dylanmuszel.data.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Use case to search products by a query.
 */
class SearchProductsUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend operator fun invoke(query: String) = withContext(Dispatchers.IO) {
        repository.search(query)
    }
}
