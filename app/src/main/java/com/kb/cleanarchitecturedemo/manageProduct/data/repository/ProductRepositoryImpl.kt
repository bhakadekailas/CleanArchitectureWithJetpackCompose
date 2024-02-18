package com.kb.cleanarchitecturedemo.manageProduct.data.repository

import arrow.core.Either
import com.kb.cleanarchitecturedemo.manageProduct.data.mapper.toNetworkError
import com.kb.cleanarchitecturedemo.manageProduct.data.remote.ProductsApi
import com.kb.cleanarchitecturedemo.manageProduct.domain.model.NetworkError
import com.kb.cleanarchitecturedemo.manageProduct.domain.model.ProductDataModel
import com.kb.cleanarchitecturedemo.manageProduct.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productsApi: ProductsApi) : ProductRepository {
    override suspend fun getProducts(): Either<NetworkError, List<ProductDataModel>> {
        return Either.catch {
            productsApi.getProducts()
        }.mapLeft { it.toNetworkError() }
    }
}