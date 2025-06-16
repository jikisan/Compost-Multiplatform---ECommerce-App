package org.jikisan.cmpecommerceapp.viewmodel

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.jikisan.cmpecommerceapp.model.Product

expect fun createHttpClient(): HttpClient

class ProductApi {

    private val BASE_URL = "https://fakestoreapi.com"



    suspend fun fetchAllProducts(): List<Product> {
        return try {
            createHttpClient().get("$BASE_URL/products").body()
        } catch (e: Exception) {
            println("Error fetching products: ${e.message}")
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun fetchProductById(id: Int): Product? {
        return try {
            createHttpClient().get("$BASE_URL/products/$id").body()
        } catch (e: Exception) {
            println("Error fetching product by ID $id: ${e.message}")
            e.printStackTrace()
            null
        }
    }
}
