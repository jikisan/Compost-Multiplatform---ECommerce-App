package org.jikisan.cmpecommerceapp.view.screens.cartscreen

import org.jikisan.cmpecommerceapp.model.CartItem
import org.jikisan.cmpecommerceapp.model.Product

data class CartUiState(
    val cartItem: CartItem? = null,
    val isLoading: Boolean = true,
    val singleProductDetail: Product? = null,
    val cartProductsDetail: List<Product> = emptyList(),
    val error: String? = null
)
