package org.jikisan.cmpecommerceapp.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartProducts(
    @SerialName("productId")
    val productId: Int,
    @SerialName("quantity")
    val quantity: Int
)