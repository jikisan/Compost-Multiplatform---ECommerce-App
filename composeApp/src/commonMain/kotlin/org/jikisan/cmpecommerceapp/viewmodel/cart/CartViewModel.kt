package org.jikisan.cmpecommerceapp.viewmodel.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jikisan.cmpecommerceapp.model.CartProducts
import org.jikisan.cmpecommerceapp.model.Product
import org.jikisan.cmpecommerceapp.view.screens.cartscreen.CartUiState

class CartViewModel(private val repository: CartRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    init {
        loadCartItems()
    }

    fun loadCartItems() {
        viewModelScope.launch {
            try {
                _uiState.value = CartUiState(isLoading = true)
                repository.getSingleCartItem().collectLatest { item ->

                    item?.let {
//                        loadProductDetail(it.products)
                        _uiState.value = CartUiState(
                            isLoading = false,
                            cartItem = it,
                        )
                    } ?: run {
                        _uiState.value = CartUiState(
                            isLoading = false,
                            error = "Cart is empty"
                        )
                    }


                }
            } catch (e: Exception) {
                _uiState.value = CartUiState(
                    isLoading = false,
                    error = e.message ?: "An error occurred"
                )
            }

        }
    }

    fun loadSingleProductDetail(id: String): Product? {

        uiState.value.cartProductsDetail?.let { details ->
            details.forEach { detail ->
                if (detail.id.toString() == id) {
                    return detail
                }
            }
        }
        return null
    }

    fun loadProductDetail(cartProducts: List<CartProducts>) {
        viewModelScope.launch {
            try {
                // Initialize an empty list to store the loaded products
                val loadedProducts = mutableListOf<Product>()

                // Iterate through each product in the cart item
                cartProducts.forEach { product ->
                    // Fetch detailed product information for each product ID
                    repository.getProductDetail(product.productId.toString()).collectLatest() { productDetail ->
                        // Add the collected product to the list
                        productDetail?.let {
                            loadedProducts.add(it)

                            _uiState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    cartProductsDetail = loadedProducts
                                )
                            }
                        }

                    }
                }
            } catch (e: Exception) {
                // TODO: Add proper error handling and logging
                // Currently empty catch block - should be handled properly
            }
        }
    }


}