package org.jikisan.cmpecommerceapp.view.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.jetbrains.compose.resources.painterResource

@Composable
fun BottomNavBar(
    navHostController: NavHostController,
    bottomNavItems: List<NavigationItem>,
    modifier: Modifier
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth().wrapContentHeight(),
    ) {

        bottomNavItems.iterator().forEach { item ->

            val currentDestination = navHostController.currentBackStackEntryAsState().value?.destination?.route
            val isSelected = item.route == currentDestination

            NavigationBarItem(
                icon = {
                    item.icon?.let { icon ->
                        Icon(
                            painter = painterResource(icon),
                            contentDescription = item.title
                        )
                    }
                },                label = { Text(
                    text = item.title,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp
                ) },
                selected = isSelected,
                onClick = {
                    if (item.route != currentDestination) {
                        navHostController.navigate(route = item.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            navHostController.graph.findStartDestination().route?.let {
                                popUpTo(it) {
                                    saveState = true
                                }
                            }
                            // Avoid multiple copies of the same destination
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                }
            )
        }

    }
}