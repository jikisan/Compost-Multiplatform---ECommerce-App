package org.jikisan.cmpecommerceapp.view.screens.productdetailscreen

import org.jikisan.cmpecommerceapp.model.Product

data class DetailUiState(
    var isLoading: Boolean = true,
    var product: Product? = null,
    var error: String? = null
)
