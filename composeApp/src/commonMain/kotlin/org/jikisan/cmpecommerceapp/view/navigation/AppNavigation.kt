package org.jikisan.cmpecommerceapp.view.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.jikisan.cmpecommerceapp.view.screens.cartscreen.CartScreen
import org.jikisan.cmpecommerceapp.view.screens.homescreen.HomeScreen
import org.jikisan.cmpecommerceapp.view.screens.settingsscreen.SettingsScreen

@Composable
fun AppNavigation(
    navHostController: NavHostController,
    modifier: Modifier,
    innerPadding: PaddingValues
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val topPadding = innerPadding.calculateTopPadding()

        NavHost(
            navHostController,
            startDestination = NavigationItem.Home.route
        ) {
            composable(NavigationItem.Home.route) {
                HomeScreen(navHostController, modifier, topPadding)
            }
            composable(NavigationItem.Cart.route) {
                CartScreen(navHostController, modifier, topPadding)
            }
            composable(NavigationItem.Settings.route) {
                SettingsScreen(navHostController, modifier, topPadding)
            }
        }
    }
}