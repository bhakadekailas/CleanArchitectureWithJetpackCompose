package com.kb.cleanarchitecturedemo.manageProduct.data.remote

import com.kb.cleanarchitecturedemo.manageProduct.domain.model.ProductDataModel
import retrofit2.http.GET

interface ProductsApi {
    @GET("products")
    suspend fun getProducts(): List<ProductDataModel>
}