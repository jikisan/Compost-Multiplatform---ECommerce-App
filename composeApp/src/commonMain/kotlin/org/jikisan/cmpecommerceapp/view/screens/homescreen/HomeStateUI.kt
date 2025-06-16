package org.jikisan.cmpecommerceapp.view.screens.homescreen

import org.jikisan.cmpecommerceapp.model.Product

data class HomeStateUI(
    val isLoading: Boolean = true,
    val products: List<Product> = emptyList(),
)
