package org.jikisan.cmpecommerceapp.viewmodel.home

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jikisan.cmpecommerceapp.model.Product
import org.jikisan.cmpecommerceapp.viewmodel.ProductApi

class HomeRepository(private val productApi: ProductApi) {
    fun getProducts(): Flow<List<Product>> = flow {
        try {
            val products = productApi.fetchAllProducts()
            emit(products)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}