package org.jikisan.cmpecommerceapp.view.screens.cartscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jikisan.cmpecommerceapp.model.Product
import org.jikisan.cmpecommerceapp.viewmodel.cart.CartViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    topPadding: Dp,
    viewModel: CartViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = topPadding, start = 8.dp, end = 8.dp, bottom = topPadding),
    ) {
        when {
            uiState.isLoading -> LoadingScreen()

            uiState.error != null -> ErrorScreen(uiState)

            uiState.cartItem?.products?.isNotEmpty() == true -> {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Cart Items List
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        uiState.cartItem?.let { cartItem ->

                            items(items = cartItem.products) { cartProduct ->

                                val id = cartProduct.productId.toString()
                                LaunchedEffect(cartItem.products) {
                                    viewModel.loadProductDetail(cartItem.products)
                                }
//                                Row(Modifier.fillMaxWidth().height(50.dp).background(MaterialTheme.colorScheme.primaryContainer)) {}
                                when {
                                    uiState.cartProductsDetail.isEmpty() -> LoadingScreen()

                                    else -> {
                                        uiState.cartProductsDetail.let { details ->
                                            details.forEach { product ->
                                                if (product.id.toString() == id) {
                                                    CartItemCard(
                                                        cartProduct = cartProduct,
                                                        product = product,
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }


                            }

                        }

                    }

                    uiState.cartProductsDetail.let { cartProductsDetail ->
                        // Total Section
                        CartTotalSection(
                            cartProducts = uiState.cartItem!!.products,
                            products = uiState.cartProductsDetail,
                            modifier = Modifier.padding(16.dp)
                        )
                    }


                }
            }

            else -> EmptyCartScreen()
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(uiState: CartUiState) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = uiState.error ?: "An error occurred",
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
fun EmptyCartScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your cart is empty",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Add some products to get started!",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


@Composable
@Preview
fun CartScreenPreview() {
    CartScreen(
        navHostController = rememberNavController(),
        modifier = Modifier,
        topPadding = 0.dp,
    )
}