package com.kb.cleanarchitecturedemo.manageProduct.data.mapper

import com.kb.cleanarchitecturedemo.manageProduct.domain.model.ApiError
import com.kb.cleanarchitecturedemo.manageProduct.domain.model.NetworkError
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toNetworkError(): NetworkError {
    val error = when (this) {
        is IOException -> ApiError.NetworkError
        is HttpException -> ApiError.UnknownResponse
        else -> ApiError.UnknownError
    }
    return NetworkError(error = error, t = this)
}