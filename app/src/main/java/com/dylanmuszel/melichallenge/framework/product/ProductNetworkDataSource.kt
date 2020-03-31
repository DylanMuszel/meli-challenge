package com.dylanmuszel.melichallenge.framework.product

import arrow.core.Either
import com.dylanmuszel.core.fp.Failure
import com.dylanmuszel.core.fp.NetworkConnection
import com.dylanmuszel.core.fp.ServerError
import com.dylanmuszel.data.ProductDataSource
import com.dylanmuszel.domain.Product
import com.dylanmuszel.melichallenge.framework.core.network.ConnectivityInfo
import retrofit2.HttpException

/**
 * Network implementation for the [ProductDataSource].
 */
class ProductNetworkDataSource(
    private val connectivityInfo: ConnectivityInfo,
    private val productService: ProductService
) : ProductDataSource {

    /**
     * Search products by a query.
     *
     * If the device is offline, it'll return a [NetworkConnection]
     * If the server throws an unexpected error, it'll return a [ServerError]
     */
    override suspend fun search(query: String): Either<Failure, List<Product>> {

        if (!connectivityInfo.isOnline) {
            return Either.Left(NetworkConnection)
        }

        return try {
            Either.Right(productService.search(query).results)
        } catch (exception: HttpException) {
            Either.Left(ServerError(exception))
        }
    }
}