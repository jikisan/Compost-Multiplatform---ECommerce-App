package org.jikisan.cmpecommerceapp

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jikisan.cmpecommerceapp.view.navigation.AppNavigation
import org.jikisan.cmpecommerceapp.view.navigation.BottomNavBar
import org.jikisan.cmpecommerceapp.view.navigation.NavigationItem
import org.jikisan.cmpecommerceapp.view.navigation.TopBar

@Composable
fun App() {

    MaterialTheme {

        // Remember NavController to maintain navigation state
        val navHostController = rememberNavController()
        val isScrollingDown = remember { mutableStateOf(false) }

        val isTopLevelDestination =
            navHostController.currentBackStackEntryAsState().value?.destination?.route in topLevelDestinations.map { it.route }

        val currentRoute =
            navHostController.currentBackStackEntryAsState().value?.destination?.route
        val showTopBar = currentRoute != NavigationItem.Details.route

        // Animate top bar translation (slide up/down)
        val topBarTranslationY by animateFloatAsState(
            targetValue = if (isScrollingDown.value) -200f else 0f, // Negative value slides up
            animationSpec = tween(durationMillis = 300),
            label = "TopBarTranslation"
        )

        // Animate top bar background fade (0f = fully transparent, 1f = fully opaque)
        val topBarAlpha by animateFloatAsState(
            targetValue = if (isScrollingDown.value) 0f else 1f, // Fade out when scrolling down
            animationSpec = tween(durationMillis = 300),
            label = "TopBarAlpha"
        )

        // Animate bottom bar translation (slide down/up)
        val bottomBarTranslationY by animateFloatAsState(
            targetValue = if (isScrollingDown.value) 240f else 0f, // Positive value slides down
            animationSpec = tween(durationMillis = 300),
            label = "BottomBarTranslation"
        )

        // Animate bottom bar shrink (height reduces when scrolling)
        val bottomBarHeight by animateDpAsState(
            targetValue = if (isScrollingDown.value) 0.dp else 56.dp,
            animationSpec = tween(durationMillis = 300),
            label = "BottomBarHeight"
        )

        // Detect scroll direction with threshold to prevent flickering on small scrolls
        val scrollThreshold = 10f
        val accumulatedScroll = remember { mutableStateOf(0f) }

        val nestedScrollConnection = remember {
            object : NestedScrollConnection {
                override fun onPreScroll(
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    // Only respond to vertical scrolls
                    if (available.y != 0f) {
                        accumulatedScroll.value += available.y

                        // Update state only when threshold is exceeded
                        if (accumulatedScroll.value < -scrollThreshold) {
                            isScrollingDown.value = true
                            accumulatedScroll.value = 0f
                        } else if (accumulatedScroll.value > scrollThreshold) {
                            isScrollingDown.value = false
                            accumulatedScroll.value = 0f
                        }
                    }
                    return Offset.Zero
                }
            }
        }

        Scaffold(
            topBar = {
                if (showTopBar) {
                    TopBar(
                        topBarAlpha = topBarAlpha,
                        translationY = topBarTranslationY
                    )
                }
            },
            bottomBar = {
                if (isTopLevelDestination) {
                    BottomNavBar(
                        navHostController = navHostController,
                        bottomNavItems = topLevelDestinations,
                        modifier = Modifier.graphicsLayer {
                            // Apply translation animation (slide down/up)
                            translationY = bottomBarTranslationY
                        }
                    )
                }


            },
        ) { innerPadding ->
            AppNavigation(
                navHostController,
                Modifier
                    .nestedScroll(nestedScrollConnection)
                    .padding(
                        start = 0.dp,
                        end = 0.dp,
                        top = 0.dp,
                        bottom = 0.dp,
//                            top = innerPadding.calculateTopPadding(),
//                            bottom = innerPadding.calculateBottomPadding()
                    )
                    .fillMaxWidth(),
//                    topPadding = 0.dp,
                innerPadding = innerPadding
            )
        }
    }

}

val topLevelDestinations = listOf(
    NavigationItem.Home,
    NavigationItem.Cart,
    NavigationItem.Settings,
)

