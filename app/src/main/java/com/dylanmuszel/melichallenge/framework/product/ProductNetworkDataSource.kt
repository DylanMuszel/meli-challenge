package com.dylanmuszel.melichallenge.framework.product

import arrow.core.Either
import com.dylanmuszel.core.fp.Failure
import com.dylanmuszel.core.fp.NetworkConnectionFailure
import com.dylanmuszel.core.fp.ServerFailure
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
     * If the device is offline, it'll return a [NetworkConnectionFailure]
     * If the server throws an unexpected error, it'll return a [ServerFailure]
     */
    override suspend fun search(query: String): Either<Failure, List<Product>> {

        if (!connectivityInfo.isOnline) {
            return Either.Left(NetworkConnectionFailure)
        }

        return try {
            Either.Right(productService.search(query).results)
        } catch (exception: HttpException) {
            Either.Left(ServerFailure(exception))
        }
    }
}
