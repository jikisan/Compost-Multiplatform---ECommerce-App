package org.jikisan.cmpecommerceapp.view.navigation

import cmpecommerceapp.composeapp.generated.resources.Res
import cmpecommerceapp.composeapp.generated.resources.cart_icon
import cmpecommerceapp.composeapp.generated.resources.home_icon
import cmpecommerceapp.composeapp.generated.resources.settings_icon
import org.jetbrains.compose.resources.DrawableResource

sealed class NavigationItem(
    val route: String,
    val title: String,
    val icon: DrawableResource? = null,
) {
    data object Home : NavigationItem("home", "Home", Res.drawable.home_icon)
    data object Cart : NavigationItem("cart", "Cart", Res.drawable.cart_icon)
    data object Settings : NavigationItem("setting", "Settings", Res.drawable.settings_icon)
    data object Details : NavigationItem("details/{productId}", "Details")
}