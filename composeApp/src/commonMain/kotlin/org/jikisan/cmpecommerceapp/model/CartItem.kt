package org.jikisan.cmpecommerceapp.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartItem(
    @SerialName("date")
    val date: String,
    @SerialName("id")
    val id: Int,
    @SerialName("products")
    val products: List<CartProducts>,
    @SerialName("userId")
    val userId: Int,
    @SerialName("__v")
    val v: Int
)