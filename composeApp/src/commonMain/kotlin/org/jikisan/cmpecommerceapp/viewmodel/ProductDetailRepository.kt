package org.jikisan.cmpecommerceapp.viewmodel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jikisan.cmpecommerceapp.model.Product

class ProductDetailRepository(private val productApi: ProductApi) {

    fun getProductDetail(id: String): Flow<Product?> = flow {
        try {
            val product = productApi.fetchProductById(id)
            emit(product)
        }
        catch (e: Exception) {
            println("Error fetching product details: ${e.message}")
            emit(null)
        }
    }
}
