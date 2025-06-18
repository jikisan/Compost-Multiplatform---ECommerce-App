package org.jikisan.cmpecommerceapp.viewmodel.productdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jikisan.cmpecommerceapp.view.screens.productdetailscreen.DetailUiState

class ProductDetailViewModel(private val productDetailRepository: ProductDetailRepository): ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    init {

    }

    fun loadProductDetail(id: String) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true, error = null) }

                productDetailRepository.getProductDetail(id).collect { product ->
                    product?.let {
                        _uiState.update { state ->
                            state.copy(
                                product = it,
                                isLoading = false,
                                error = null
                            )
                        }
                    } ?: run {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                error = "Product not found"
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        error = e.message ?: "An error occurred while loading product details"
                    )
                }
            }
        }
    }


}