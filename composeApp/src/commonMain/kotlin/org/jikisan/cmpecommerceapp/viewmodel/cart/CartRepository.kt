package org.jikisan.cmpecommerceapp.viewmodel.cart

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jikisan.cmpecommerceapp.model.CartItem
import org.jikisan.cmpecommerceapp.model.Product
import org.jikisan.cmpecommerceapp.view.screens.cartscreen.CartUiState
import org.jikisan.cmpecommerceapp.viewmodel.ProductApi

class CartRepository(private val prodApi: ProductApi) {

    fun getCartItems(): Flow<List<CartItem>> = flow {
        try {
            val cartItems = prodApi.fetchCartItems()
            emit(cartItems)
        }
        catch (e: Exception) {
            emit(emptyList())
        }
    }

    fun getSingleCartItem(): Flow<CartItem?> = flow {
        try {
            val cartItem = prodApi.fetchSingleCartItem()
            emit(cartItem)
        }
        catch (e: Exception) {
            emit(null)
        }
    }

    fun getProductDetail(id: String): Flow<Product?> = flow {
        try {
            val product = prodApi.fetchProductById(id)
            emit(product)
        }
        catch (e: Exception) {
            println("Error fetching product details: ${e.message}")
            emit(null)
        }
    }

}