package com.kb.cleanarchitecturedemo.manageProduct.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kb.cleanarchitecturedemo.manageProduct.domain.model.ProductDataModel
import com.kb.cleanarchitecturedemo.manageProduct.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ProductsViewState())
    val state = _state.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            productsRepository.getProducts()
                .onRight { products ->
                    _state.update {
                        it.copy(products = products)
                    }
                }.onLeft { error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                }
            _state.update { it.copy(isLoading = false) }
        }
    }
}

data class ProductsViewState(
    val isLoading: Boolean = false,
    val products: List<ProductDataModel> = emptyList(),
    val error: String? = null
)