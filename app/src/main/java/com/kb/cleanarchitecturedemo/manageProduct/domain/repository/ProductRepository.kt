package com.kb.cleanarchitecturedemo.manageProduct.domain.repository

import arrow.core.Either
import com.kb.cleanarchitecturedemo.manageProduct.domain.model.NetworkError
import com.kb.cleanarchitecturedemo.manageProduct.domain.model.ProductDataModel

interface ProductRepository {
    suspend fun getProducts(): Either<NetworkError, List<ProductDataModel>>
}