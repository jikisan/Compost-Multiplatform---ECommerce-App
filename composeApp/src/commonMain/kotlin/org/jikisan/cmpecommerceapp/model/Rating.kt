package org.jikisan.cmpecommerceapp.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    @SerialName("count")
    val count: Int,
    @SerialName("rate")
    val rate: Double
)