package org.jikisan.cmpecommerceapp.view.screens.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jikisan.cmpecommerceapp.viewmodel.ProductRepository
import kotlin.math.log

class HomeViewModel(val productRepository: ProductRepository): ViewModel() {


    private var _uiState = MutableStateFlow(HomeStateUI(products = emptyList()))
    val uiState: StateFlow<HomeStateUI> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {

        viewModelScope.launch {
            productRepository.getProducts().collectLatest {
                _uiState.update { products ->
                    products.copy(products = it, isLoading = false)
                }
            }
        }

    }


}