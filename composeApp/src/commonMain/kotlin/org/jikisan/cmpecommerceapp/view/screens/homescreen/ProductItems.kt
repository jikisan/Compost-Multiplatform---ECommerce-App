package org.jikisan.cmpecommerceapp.view.screens.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cmpecommerceapp.composeapp.generated.resources.Res
import cmpecommerceapp.composeapp.generated.resources.star_icon
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jikisan.cmpecommerceapp.model.Product
import org.jikisan.cmpecommerceapp.model.Rating



@Composable
fun ProductItemCard(
    product: Product,
    onAddToCart: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Product Image
//            AsyncImage(
//                model = product.image,
//                contentDescription = "Product image: ${product.title}",
//                imageLoader = getImageLoader(),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(180.dp)
//                    .clip(RoundedCornerShape(8.dp)),
//                contentScale = ContentScale.Fit
//            )

            AsyncImage(
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(product.image)
                    .crossfade(true)
                    .build(),
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )


            Spacer(modifier = Modifier.height(8.dp))

            // Product Title
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Price
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Display star rating
                Row {
                    val rating = product.rating.rate.toInt()
                    val maxRating = 5

                    repeat(rating) {
                        Icon(
                            painter = painterResource(Res.drawable.star_icon),
                            contentDescription = "Filled star",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    if (rating < maxRating) {
                        repeat(maxRating - rating) {
                            Icon(
                                painter = painterResource(Res.drawable.star_icon),
                                contentDescription = "Empty star",
                                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "(${product.rating.count})",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Add to Cart Button
//            Button(
//                onClick = { onAddToCart(product) },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text("Add to Cart")
//            }
        }
    }
}

// Preview Composable for Android Studio
@Preview
@Composable
fun ProductItemCardPreview() {
    MaterialTheme {
        ProductItemCard(
            product = Product(
                id = 1,
                title = "Sample Product",
                price = 29.99,
                description = "This is a sample product description.",
                category = "electronics",
                image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                rating = Rating(rate = 4.5, count = 120)
            ),
            onAddToCart = {}
        )
    }
}