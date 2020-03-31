package com.dylanmuszel.usecases.product

import arrow.core.Either
import com.dylanmuszel.core.fp.FeatureFailure
import com.dylanmuszel.data.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Use case to search products by a query.
 * If the query is empty, it'll return an [EmptySearch] failure.
 */
class SearchProductsUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend operator fun invoke(query: String) = withContext(Dispatchers.IO) {
        if (query.isNotBlank()) {
            repository.search(query)
        } else {
            Either.Left(EmptySearch)
        }

    }
}

/** A feature failure thrown when the search is empty. */
object EmptySearch : FeatureFailure()