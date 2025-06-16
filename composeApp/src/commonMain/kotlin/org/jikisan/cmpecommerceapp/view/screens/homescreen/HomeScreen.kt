package org.jikisan.cmpecommerceapp.view.screens.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue 
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jikisan.cmpecommerceapp.viewmodel.ProductApi
import org.jikisan.cmpecommerceapp.viewmodel.ProductRepository

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    modifier: Modifier,
    topPadding: Dp,
//    viewModel: HomeViewModel = koinViewModel<HomeViewModel>()
) {
    Box(
        modifier.fillMaxSize().padding(top = topPadding), Alignment.Center
    ) {

        val productApi = remember { ProductApi() }
        val productRepository = remember { ProductRepository(productApi) }
        val viewModel = remember { HomeViewModel(productRepository) }
        val uiState by viewModel.uiState.collectAsState()

        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else {
            uiState.products.let { products ->

                if (products.isNotEmpty()) {

                    LazyColumn(modifier = Modifier.fillMaxSize()) {

                        items(products.chunked(2).size) { rowIndex ->

                            Row(modifier = Modifier.fillMaxWidth()) {
                                products.chunked(2)[rowIndex].forEach { product ->
                                    Box(modifier = Modifier.weight(1f).padding(4.dp)) {
                                        ProductItemCard(
                                            product = product,
                                            onAddToCart = {}
                                        )
                                    }
                                }
                                // If there's an odd number of items, the last row will have one item.
                                // Add an empty Box to maintain the two-column structure.
                                if (products.chunked(2)[rowIndex].size == 1) {
                                    Box(modifier = Modifier.weight(1f)) {}
                                }
                            }
                        }
                    }
                } else {
                    Text("No products available")
                }
            }


        }
    }

}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController(), modifier = Modifier, topPadding = 0.dp)
}