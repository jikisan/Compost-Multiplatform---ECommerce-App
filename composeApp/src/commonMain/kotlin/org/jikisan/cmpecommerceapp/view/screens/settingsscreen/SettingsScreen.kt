package org.jikisan.cmpecommerceapp.view.screens.settingsscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SettingsScreen(navHostController: NavHostController, modifier: Modifier, topPadding: Dp) {
    Box(
        modifier.fillMaxSize().padding(top = topPadding),
        Alignment.Center
    ) {
        Text(text = "Settings Screen")
    }
}


@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(rememberNavController(), Modifier, 0.dp)
}