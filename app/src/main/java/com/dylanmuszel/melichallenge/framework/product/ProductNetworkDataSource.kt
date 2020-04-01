package com.dylanmuszel.melichallenge.framework.product

import arrow.core.Either
import com.dylanmuszel.core.fp.Failure
import com.dylanmuszel.core.fp.NetworkConnectionFailure
import com.dylanmuszel.core.fp.ServerFailure
import com.dylanmuszel.data.ProductDataSource
import com.dylanmuszel.domain.Product
import com.dylanmuszel.melichallenge.framework.core.Logger
import com.dylanmuszel.melichallenge.framework.core.network.NoNetworkException

/**
 * Network implementation for the [ProductDataSource].
 */
class ProductNetworkDataSource(
    private val logger: Logger,
    private val productService: ProductService
) : ProductDataSource {

    /**
     * Search products by a query.
     *
     * If the device is offline, it'll return a [NetworkConnectionFailure]
     * If the server throws an unexpected error, it'll return a [ServerFailure]
     */
    override suspend fun search(query: String): Either<Failure, List<Product>> {

        return try {
            Either.Right(productService.search(query).results)
        } catch (exception: NoNetworkException) {
            Either.Left(NetworkConnectionFailure)
        } catch (e: Throwable) {
            // Logging unexpected server error as soon as catched
            logger.e(TAG, "Unexpected error while searching $query", e)
            Either.Left(ServerFailure(e))
        }
    }

    companion object {
        private const val TAG = "ProductListPresenter"
    }
}
